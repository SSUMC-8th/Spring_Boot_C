package umc.spring.service.reviewservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.exception.GeneralException;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.memberrepository.MemberRepository;
import umc.spring.repository.storerepository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public ReviewResponseDTO.CreateReviewResultDTO createReview(ReviewRequestDTO.CreateReviewDTO request, Long memberId) {
        // 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // 가게 조회
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.ARTICLE_NOT_FOUND));

        // 리뷰 엔티티 생성
        Review review = ReviewConverter.toReview(request, member, store);

        // 리뷰 저장
        review = reviewRepository.save(review);

        // 해당 가게의 평균 평점 업데이트 로직이 필요하다면 여기에 추가
        updateStoreRating(store);

        // 해당 회원이 이 가게의 미션을 수행중이고, 리뷰를 작성하지 않았다면 미션 상태 업데이트
        updateMemberMissionStatus(member, store);

        return ReviewConverter.toCreateReviewResultDTO(review);
    }

    // 가게의 평균 평점 업데이트 메서드
    private void updateStoreRating(Store store) {
        // 가게의 모든 리뷰를 가져와서 평균 평점 계산
        List<Review> reviews = store.getReviewList();

        if (reviews != null && !reviews.isEmpty()) {
            double averageRating = reviews.stream()
                    .mapToDouble(Review::getRating)
                    .average()
                    .orElse(0.0);

            // 평균 평점 소수점 첫째 자리까지 반올림
            double roundedRating = Math.round(averageRating * 10) / 10.0;

            // 가게 평점 업데이트
            store.updateRating(roundedRating);
        }
    }

    // 회원의 미션 상태 업데이트 메서드
    private void updateMemberMissionStatus(Member member, Store store) {
        // 이 회원이 이 가게에 대한 미션을 진행 중인지 확인
        member.getMemberMissionList().stream()
                .filter(mm -> mm.getStatus() == MissionStatus.IN_PROGRESS &&
                        mm.getMission().getStore().getId().equals(store.getId()) &&
                        !mm.getIsReviewed())
                .findFirst()
                .ifPresent(memberMission -> {
                    // 리뷰 작성 상태로 업데이트
                    memberMission.updateReviewStatus(true);

                    // 필요에 따라 여기서 포인트 적립 등의 로직을 추가할 수 있음
                    Long missionPoint = memberMission.getMission().getPoint();
                    member.addPoint(missionPoint);
                });
    }
}
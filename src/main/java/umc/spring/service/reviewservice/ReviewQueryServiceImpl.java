package umc.spring.service.reviewservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.exception.GeneralException;
import umc.spring.repository.ReviewRepository;
import umc.spring.web.dto.ReviewResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional(readOnly = true)
    public ReviewResponseDTO.ReviewDetailDTO getReviewDetail(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.ARTICLE_NOT_FOUND));

        return ReviewConverter.toReviewDetailDTO(review);
    }
}

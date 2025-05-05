package umc.spring.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

/**
 * INSERT INTO review (store_id, user_id, rating, content, created_at, updated_at)
 * VALUES (1, 1234, 5.0, '음 너무 맛있어요! ~~~', NOW(6), NOW(6));
 */

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final EntityManager entityManager;

    @Transactional
    public Review insertReview(Long storeId, Long memberId, Double rating, String content) {

        // Store와 Member 엔티티 조회
        Store store = entityManager.find(Store.class, storeId);
        Member member = entityManager.find(Member.class, memberId);

        if (store == null || member == null) {
            throw new IllegalArgumentException("Store 또는 Member를 찾을 수 없습니다.");
        }

        // Review 엔티티 생성
        Review review = Review.builder()
                .store(store)
                .member(member)
                .rating(rating)
                .content(content)
                .build();

        // 엔티티 저장 및 반환
        entityManager.persist(review);
        return review;

    }
}

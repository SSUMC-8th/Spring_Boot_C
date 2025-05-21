package umc.spring.service.reviewservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewResponseDTO;

public interface ReviewQueryService {

    ReviewResponseDTO.ReviewDetailDTO getReviewDetail(Long reviewId);

    Page<Review> getMyReviewsForStore(Long storeId, Long memberId, Pageable pageable);
}

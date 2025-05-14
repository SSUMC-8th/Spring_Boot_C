package umc.spring.service.reviewservice;

import umc.spring.web.dto.ReviewResponse;

public interface ReviewQueryService {

    ReviewResponse.ReviewDetailDTO getReviewDetail(Long reviewId);
}

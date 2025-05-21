package umc.spring.service.reviewservice;

import umc.spring.web.dto.ReviewResponseDTO;

public interface ReviewQueryService {

    ReviewResponseDTO.ReviewDetailDTO getReviewDetail(Long reviewId);
}

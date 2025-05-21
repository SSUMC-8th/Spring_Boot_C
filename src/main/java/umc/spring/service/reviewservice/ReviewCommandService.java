package umc.spring.service.reviewservice;

import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

public interface ReviewCommandService {
    ReviewResponseDTO.CreateReviewResultDTO createReview(ReviewRequestDTO.CreateReviewDTO request, Long memberId);
}

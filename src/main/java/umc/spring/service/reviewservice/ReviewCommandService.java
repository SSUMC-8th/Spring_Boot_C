package umc.spring.service.reviewservice;

import umc.spring.dto.web.ReviewRequestDTO;
import umc.spring.dto.web.ReviewResponseDTO;

public interface ReviewCommandService {
    ReviewResponseDTO.CreateReviewResultDTO createReview(ReviewRequestDTO.CreateReviewDTO request, Long memberId);
}

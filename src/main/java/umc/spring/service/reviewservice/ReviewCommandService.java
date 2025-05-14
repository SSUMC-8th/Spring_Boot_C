package umc.spring.service.reviewservice;

import umc.spring.web.dto.ReviewRequest;
import umc.spring.web.dto.ReviewResponse;

public interface ReviewCommandService {
    ReviewResponse.CreateReviewResultDTO createReview(ReviewRequest.CreateReviewDTO request, Long memberId);
}

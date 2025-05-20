package umc.spring.service.ReviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

import javax.transaction.Transactional;

public interface ReviewCommandService {
    Review joinReview(Long storeId, ReviewRequestDTO.ReviewDTO request);
}

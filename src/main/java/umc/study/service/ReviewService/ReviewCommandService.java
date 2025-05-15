package umc.study.service.ReviewService;

import umc.study.web.dto.requestDTO.ReviewRequestDTO;
import umc.study.web.dto.responseDTO.ReviewResponseDTO;

public interface ReviewCommandService {
    ReviewResponseDTO.CreateResult createReview(ReviewRequestDTO.CreateReview dto);
}

package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static Review toReview(StoreRequestDTO.ReveiwDTO request){
        return Review.builder()
                .title(request.getTitle())
                .rating(request.getRating())
                .content(request.getContent())
                .date(request.getDate())
                .build();
    }

    public static StoreResponseDTO.reviewResultDTO toReveiwResultDTO(Review review) {
        return StoreResponseDTO.reviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}

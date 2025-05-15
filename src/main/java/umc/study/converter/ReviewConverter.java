package umc.study.converter;

import org.springframework.stereotype.Component;
import umc.study.domain.Review;
import umc.study.domain.ReviewImage;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.web.dto.requestDTO.ReviewRequestDTO;
import umc.study.web.dto.responseDTO.ReviewResponseDTO;

import java.util.List;

@Component
public class ReviewConverter {

    public Review toReviewEntity(ReviewRequestDTO.CreateReview dto, Store store, User user) {
        return Review.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .score(dto.getScore())
                .store(store)
                .user(user)
                .build();
    }

    public List<ReviewImage> toReviewImages(List<String> imageUrls, Review review) {
        if (imageUrls == null) return List.of();
        return imageUrls.stream()
                .map(url -> ReviewImage.builder()
                        .imageUrl(url)
                        .review(review)
                        .build())
                .toList();
    }

    public ReviewResponseDTO.CreateResult toResponse(Review review, List<ReviewImage> images) {
        List<String> urls = images.stream().map(ReviewImage::getImageUrl).toList();

        return new ReviewResponseDTO.CreateResult(
                review.getId(),
                review.getStore().getName(),
                review.getUser().getName(),
                review.getTitle(),
                review.getScore(),
                urls
        );
    }
}


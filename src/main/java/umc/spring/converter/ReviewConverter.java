package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.CreateReviewDTO request, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .rating(request.getRating())
                .content(request.getContent())
                .build();
    }

    public static ReviewImage toReviewImage(String imageUrl, Review review) {
        return ReviewImage.builder()
                .review(review)
                .imageUrl(imageUrl)
                .build();
    }

    public static ReviewResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.ReviewDetailDTO toReviewDetailDTO(Review review) {
        List<String> imageUrls = review.getReviewImageList().stream()
                .map(ReviewImage::getImageUrl)
                .collect(Collectors.toList());

        return ReviewResponseDTO.ReviewDetailDTO.builder()
                .id(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .memberId(review.getMember().getId())
                .memberName(review.getMember().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .imageUrls(imageUrls)
                .build();
    }
}
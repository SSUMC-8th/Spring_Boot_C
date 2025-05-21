package umc.study.converter;

import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.study.domain.*;
import umc.study.web.dto.requestDTO.ReviewRequestDTO;
import umc.study.web.dto.responseDTO.ReviewResponseDTO;
import umc.study.web.dto.reviewDTO.MyReviewDTO;
import umc.study.web.dto.reviewDTO.MyReviewListDTO;
import umc.study.web.dto.reviewDTO.OwnerReplyDTO;

import java.util.List;
import java.util.stream.Collectors;

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

    private static OwnerReplyDTO toOwnerReplyDTO(ReviewOwnerReply reply) {
        if (reply == null) return null;

        return OwnerReplyDTO.builder()
                .body(reply.getBody())
                .createdAt(reply.getCreatedAt().toLocalDate())
                .build();
    }

    public static MyReviewDTO toMyReviewDTO(Review review) {
        return MyReviewDTO.builder()
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt().toLocalDate())
                .storeName(review.getStore().getName())
                .reviewImages(
                        review.getReviewImageList().stream()
                                .map(ReviewImage::getImageUrl)
                                .collect(Collectors.toList())
                )
                .ownerReply(toOwnerReplyDTO(review.getOwnerReply()))
                .build();
    }

    public static MyReviewListDTO toMyReviewListDTO(Page<Review> reviewPage) {
        List<MyReviewDTO> reviewDTOList = reviewPage.getContent().stream()
                .map(ReviewConverter::toMyReviewDTO)
                .collect(Collectors.toList());

        return MyReviewListDTO.builder()
                .reviewList(reviewDTOList)
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .listSize(reviewDTOList.size())
                .build();
    }
}


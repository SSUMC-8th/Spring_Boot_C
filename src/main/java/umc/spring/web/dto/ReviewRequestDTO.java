package umc.spring.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewDTO {
        @NotNull(message = "가게 ID는 필수입니다.")
        private Long storeId;

        @NotNull(message = "평점은 필수입니다.")
        @Min(value = 1, message = "평점은 최소 1점 이상이어야 합니다.")
        @Max(value = 5, message = "평점은 최대 5점까지 가능합니다.")
        private Double rating;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Length(min = 5, max = 1000, message = "리뷰 내용은 5자 이상 1000자 이하여야 합니다.")
        private String content;

        // 선택 사항: 리뷰 이미지 URL 목록
        private List<String> imageUrls;
    }
}
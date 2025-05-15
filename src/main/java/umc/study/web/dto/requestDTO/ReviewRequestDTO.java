package umc.study.web.dto.requestDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReview {
        @NotNull
        private Long storeId;

        @NotNull
        private Long userId;

        @NotBlank
        private String title;

        @NotBlank
        private String body;

        @NotNull
        private Float score;

        private List<String> imageUrls; // 선택적
    }
}


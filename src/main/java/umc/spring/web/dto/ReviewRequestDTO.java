package umc.spring.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewDTO {
        @NotNull(message = "가게 ID는 필수입니다.")
        Long storeId;
        @NotNull(message = "회원 ID는 필수입니다.")
        Long memberId;
        String content;
        @NotNull(message = "별점은 필수입니다.")
        @Min(1) @Max(5)
        float rating;
        List<String> photos;

    }
}

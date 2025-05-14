package umc.spring.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ValidStoreId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionCreateRequestDTO {

    @Getter
    public static class MissionCreateDTO {
        @NotNull(message = "가게 ID는 필수입니다.")
        private Long storeId;

        @NotBlank(message = "미션 내용은 필수입니다.")
        private String content;

        @NotNull(message = "포인트는 필수입니다.")
        @Min(0)
        private Integer point;

        @NotNull(message = "기간은 필수입니다.")
        private LocalDate period;
    }
}

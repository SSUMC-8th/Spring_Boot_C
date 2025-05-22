package umc.spring.dto.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class StoreRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStoreDTO {
        @NotBlank(message = "가게 이름은 필수입니다.")
        private String name;

        @NotBlank(message = "가게 주소는 필수입니다.")
        private String address;

        @NotNull(message = "음식 카테고리 ID는 필수입니다.")
        private Long foodId;

        @NotNull(message = "지역 ID는 필수입니다.")
        private Long locationId;

        @PositiveOrZero(message = "평점은 0이상이어야 합니다.")
        private Double rating;

        private List<OpeningHoursDTO> openingHours;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OpeningHoursDTO {
        private DayOfWeek dayOfWeek;
        private LocalTime startingTime;
        private LocalTime endingTime;
    }
}

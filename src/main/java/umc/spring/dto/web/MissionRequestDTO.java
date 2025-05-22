package umc.spring.dto.web;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.NotAlreadyAccepted;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @NotAlreadyAccepted
    public static class AcceptMissionDTO {
        @NotNull(message = "미션 ID는 필수입니다.")
        private Long missionId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMissionDTO {
        @NotNull(message = "가게 ID는 필수입니다.")
        @ExistStore  // 가게 존재 검증
        private Long storeId;

        @NotBlank(message = "미션 내용은 필수입니다.")
        @Length(min = 5, max = 500, message = "미션 내용은 5자 이상 500자 이하여야 합니다.")
        private String content;

        @NotNull(message = "미션 포인트는 필수입니다.")
        @Min(value = 100, message = "미션 포인트는 최소 100점 이상이어야 합니다.")
        @Max(value = 10000, message = "미션 포인트는 최대 10000점까지 가능합니다.")
        private Long point;

        @NotNull(message = "미션 기간은 필수입니다.")
        @Future(message = "미션 기간은 현재 시간보다 미래여야 합니다.")
        private LocalDateTime duration;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompleteMissionDTO {
        @NotNull(message = "회원 미션 ID는 필수입니다.")
        private Long memberMissionId;

        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;
    }
}
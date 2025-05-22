package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.validation.annotation.NotAlreadyAccepted;

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
}
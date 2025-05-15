package umc.study.web.dto.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class CreateMission {
        @NotNull
        private Long storeId;

        @NotNull
        private Integer point;

        @NotNull
        private LocalDate deadline;

        @NotBlank
        private String missionSpec;
    }
}

package umc.study.web.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

public class MissionResponseDTO {

    @Getter
    @AllArgsConstructor
    public static class CreateResult {
        private Long missionId;
        private String storeName;
        private Integer point;
        private LocalDate deadline;
        private String missionSpec;
    }
}


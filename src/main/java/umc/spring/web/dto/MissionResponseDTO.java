package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AcceptMissionResultDTO {
        private Long memberMissionId;
        private Long missionId;
        private String storeName;
        private String missionContent;
        private Long point;
        private LocalDateTime acceptedAt;
        private LocalDateTime deadline;
    }
}
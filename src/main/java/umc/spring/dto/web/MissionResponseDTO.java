package umc.spring.dto.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMissionResultDTO {
        private Long missionId;
        private String storeName;
        private String content;
        private Long point;
        private LocalDateTime duration;
        private LocalDateTime createdAt;
    }

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDTO {
        private List<MissionDTO> missionList;
        private int totalPages;
        private long totalElements;
        private boolean isFirst;
        private boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDTO {
        private Long id;
        private String content;
        private Long point;
        private LocalDateTime duration;
        private String storeName;
        private Long storeId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompleteMissionResultDTO {
        private Long memberMissionId;
        private Long missionId;
        private String storeName;
        private String missionContent;
        private Long earnedPoint;
        private MissionStatus status;
        private LocalDateTime completedAt;
        private Boolean isReviewed;
    }
}
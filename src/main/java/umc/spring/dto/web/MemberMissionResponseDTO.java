package umc.spring.dto.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionListDTO {
        private List<MemberMissionDTO> memberMissionList;
        private int totalPages;
        private long totalElements;
        private boolean isFirst;
        private boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionDTO {
        private Long memberMissionId;
        private Long missionId;
        private Long storeId;

        private String storeName;
        private String missionContent;
        private Long missionPoint;
        private MissionStatus status;
        private Boolean isReviewed;
        private LocalDateTime deadline;
        private LocalDateTime startedAt;
        private Long daysRemaining;
        private Boolean isExpired;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionDetailDTO {
        private Long memberMissionId;
        private Long missionId;
        private Long storeId;

        private String storeName;
        private String storeAddress;
        private String missionContent;
        private Long missionPoint;
        private MissionStatus status;
        private Boolean isReviewed;
        private Integer authenticationNum;
        private LocalDateTime deadline;
        private LocalDateTime startedAt;
        private LocalDateTime createdAt;
        private Long daysRemaining;
        private Boolean isExpired;
    }
}
package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionCreateResultDTO{
        private Long missionId;
        private Long storeId;
        private String storeName;
        private String content;
        private Integer point;
        private LocalDate period;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewDTO {
        private Long missionId;
        private String content;
        private Integer point;
        private Integer period;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewListDTO {
        private List<MissionPreviewDTO> missions;
        private boolean hasNext;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberMissionResponseDTO {
        private Long missionId;
        private String missionTitle;
        private int daysLeft;

        @Getter
        @Builder
        public static class PageDTO {
            private List<MemberMissionResponseDTO> missions;
            private boolean hasNext;
        }
    }


}

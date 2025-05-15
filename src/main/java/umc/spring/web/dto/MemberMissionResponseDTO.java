package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberMissionResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class memberMissionResultDTO{
        @NotNull
        private Long userMissionId;
        @NotNull
        private Long missionId;
        @NotNull
        private Long UserId;
        @NotNull
        private String missionContent;
        @NotNull
        private String status;
    }
}

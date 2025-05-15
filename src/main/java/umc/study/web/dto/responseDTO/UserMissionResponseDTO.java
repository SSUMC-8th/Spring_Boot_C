package umc.study.web.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.study.domain.enums.MissionStatus;

public class UserMissionResponseDTO {

    @Getter
    @AllArgsConstructor
    public static class ChallengeResult {
        private Long userMissionId;
        private String userName;
        private String missionContent;
        private MissionStatus status;
    }
}


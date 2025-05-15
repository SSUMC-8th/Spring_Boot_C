package umc.study.web.dto.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.UserMissionUniqueCheck;

public class UserMissionRequestDTO {

    @Getter
    @UserMissionUniqueCheck  // ✅ 클래스 레벨에 부착
    public static class ChallengeRequest {

        @NotNull
        private Long userId;

        @NotNull
        private Long missionId;

        @NotBlank
        private String content;

        @NotBlank
        private String number;
    }
}

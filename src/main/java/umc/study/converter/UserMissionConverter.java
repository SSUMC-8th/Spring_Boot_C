package umc.study.converter;

import org.springframework.stereotype.Component;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.requestDTO.UserMissionRequestDTO;
import umc.study.web.dto.responseDTO.UserMissionResponseDTO;

@Component
public class UserMissionConverter {

    public UserMission toEntity(User user, Mission mission, UserMissionRequestDTO.ChallengeRequest dto) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .content(dto.getContent())
                .number(dto.getNumber())
                .status(MissionStatus.CHALLENGING)
                .build();
    }

    public UserMissionResponseDTO.ChallengeResult toResponse(UserMission userMission) {
        return new UserMissionResponseDTO.ChallengeResult(
                userMission.getId(),
                userMission.getUser().getName(),
                userMission.getContent(),
                userMission.getStatus()
        );
    }
}


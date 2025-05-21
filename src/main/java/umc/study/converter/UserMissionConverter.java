package umc.study.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.UserMissionDTO.MyOngoingMissionDTO;
import umc.study.web.dto.UserMissionDTO.MyOngoingMissionListDTO;
import umc.study.web.dto.requestDTO.UserMissionRequestDTO;
import umc.study.web.dto.responseDTO.UserMissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

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

    public static MyOngoingMissionDTO toMyOngoingDTO(UserMission userMission) {
        Mission mission = userMission.getMission();
        return MyOngoingMissionDTO.builder()
                .storeName(mission.getStore().getName())
                .title(mission.getMissionSpec())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MyOngoingMissionListDTO toMyOngoingListDTO(Page<UserMission> userMissions) {
        List<MyOngoingMissionDTO> list = userMissions.getContent().stream()
                .map(UserMissionConverter::toMyOngoingDTO)
                .collect(Collectors.toList());

        return MyOngoingMissionListDTO.builder()
                .missionList(list)
                .isFirst(userMissions.isFirst())
                .isLast(userMissions.isLast())
                .totalPage(userMissions.getTotalPages())
                .totalElements(userMissions.getTotalElements())
                .listSize(list.size())
                .build();
    }
}


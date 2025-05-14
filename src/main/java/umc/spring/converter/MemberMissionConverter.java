package umc.spring.converter;

import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.MemberMissionResponseDTO;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.memberMissionResultDTO toMemberMissionResultDTO(UserMission userMission) {
        return MemberMissionResponseDTO.memberMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .UserId(userMission.getUser().getId())
                .missionContent(userMission.getMission().getContent())
                .status(userMission.getStatus() != null ? userMission.getStatus().name() : "IN_PROGRESS")
                .build();
    }
}

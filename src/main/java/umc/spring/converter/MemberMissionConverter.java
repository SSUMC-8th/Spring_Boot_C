package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

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

    public static MissionResponseDTO.MissionUserPreViewDTO missionUserPreViewDTO(UserMission mission) {
        return MissionResponseDTO.MissionUserPreViewDTO.builder()
                .storeName(mission.getMission().getStore().getName())
                .status(mission.getStatus().toString())
                .point(mission.getMission().getPoint())
                .content(mission.getMission().getContent())
                .build();
    }

    public static MissionResponseDTO.MissionUserPreViewListDTO missionUserPreViewListDTO(Page<UserMission> missionList) {
        List<MissionResponseDTO.MissionUserPreViewDTO> missionUserPreViewDTOList = missionList.stream()
                .map(MemberMissionConverter::missionUserPreViewDTO).collect(Collectors.toList());

        return MissionResponseDTO.MissionUserPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionUserPreViewDTOList.size())
                .missionList(missionUserPreViewDTOList)
                .build();
    }
}

package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.stream.Collectors;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.CreateMissionDTO request, Store store) {
        Mission mission = Mission.builder()
                .content(request.getContent())
                .point(request.getPoint())
                .duration(request.getDuration())
                .build();

        mission.setStore(store);

        return mission;
    }

    public static MissionResponseDTO.CreateMissionResultDTO toCreateMissionResultDTO(Mission mission) {
        return MissionResponseDTO.CreateMissionResultDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .point(mission.getPoint())
                .duration(mission.getDuration())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MissionResponseDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResponseDTO.MissionDTO.builder()
                .id(mission.getId())
                .content(mission.getContent())
                .point(mission.getPoint())
                .duration(mission.getDuration())
                .storeName(mission.getStore().getName())
                .storeId(mission.getStore().getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MissionResponseDTO.MissionListDTO toMissionListDTO(Page<Mission> missionPage) {
        return MissionResponseDTO.MissionListDTO.builder()
                .missionList(missionPage.getContent().stream()
                        .map(MissionConverter::toMissionDTO)
                        .collect(Collectors.toList()))
                .totalPages(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}
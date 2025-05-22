package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
public class MissionConverter {
    public static Mission toMission(MissionRequestDTO.MissionCreateDTO dto, Store store) {
        return Mission.builder()
                .store(store)
                .missionContent(dto.getContent())
                .point(dto.getPoint())
                .period(dto.getPeriod())
                .build();
    }

    public static MissionResponseDTO.MissionCreateResultDTO toMissionCreateResponseDTO(Mission mission) {
        return MissionResponseDTO.MissionCreateResultDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .content(mission.getMissionContent())
                .point(mission.getPoint())
                .period(mission.getPeriod())
                .build();
    }
}

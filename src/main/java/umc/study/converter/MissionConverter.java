package umc.study.converter;

import org.springframework.stereotype.Component;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.web.dto.requestDTO.MissionRequestDTO;
import umc.study.web.dto.responseDTO.MissionResponseDTO;

@Component
public class MissionConverter {

    public Mission toEntity(MissionRequestDTO.CreateMission dto, Store store) {
        return Mission.builder()
                .store(store)
                .point(dto.getPoint())
                .deadline(dto.getDeadline())
                .missionSpec(dto.getMissionSpec())
                .build();
    }

    public MissionResponseDTO.CreateResult toResponse(Mission mission) {
        return new MissionResponseDTO.CreateResult(
                mission.getId(),
                mission.getStore().getName(),
                mission.getPoint(),
                mission.getDeadline(),
                mission.getMissionSpec()
        );
    }
}


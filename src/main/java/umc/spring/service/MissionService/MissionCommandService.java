package umc.spring.service.MissionService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {

    @Transactional
    Mission createMission(MissionRequestDTO.MissionCreateDTO dto);

    Mission joinMission(MissionRequestDTO.MissionCreateDTO missionRequestDTO);

}

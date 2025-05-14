package umc.spring.service.MissionService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionCreateRequestDTO;

public interface MissionCommandService {

    Mission createMission(MissionCreateRequestDTO.MissionCreateDTO dto);

    Mission joinMission(MissionCreateRequestDTO.MissionCreateDTO missionRequestDTO);
}

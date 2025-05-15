package umc.study.service.MissionService;

import umc.study.web.dto.requestDTO.MissionRequestDTO;
import umc.study.web.dto.responseDTO.MissionResponseDTO;

public interface MissionCommandService {
    MissionResponseDTO.CreateResult createMission(MissionRequestDTO.CreateMission dto);
}

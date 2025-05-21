package umc.spring.service.missionservice;

import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

public interface MissionCommandService {
    MissionResponseDTO.AcceptMissionResultDTO acceptMission(MissionRequestDTO.AcceptMissionDTO request, Long memberId);
}
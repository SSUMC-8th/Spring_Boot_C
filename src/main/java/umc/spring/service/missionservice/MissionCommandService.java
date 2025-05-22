package umc.spring.service.missionservice;

import umc.spring.dto.web.MissionRequestDTO;
import umc.spring.dto.web.MissionResponseDTO;

public interface MissionCommandService {

    MissionResponseDTO.CreateMissionResultDTO createMission(MissionRequestDTO.CreateMissionDTO request);

    MissionResponseDTO.AcceptMissionResultDTO acceptMission(MissionRequestDTO.AcceptMissionDTO request, Long memberId);
}
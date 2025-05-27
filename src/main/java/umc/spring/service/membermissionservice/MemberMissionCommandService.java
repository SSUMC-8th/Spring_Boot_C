package umc.spring.service.membermissionservice;

import umc.spring.dto.web.MissionRequestDTO;
import umc.spring.dto.web.MissionResponseDTO;

public interface MemberMissionCommandService {

    // 미션 완료 처리
    MissionResponseDTO.CompleteMissionResultDTO completeMission(MissionRequestDTO.CompleteMissionDTO request);

}
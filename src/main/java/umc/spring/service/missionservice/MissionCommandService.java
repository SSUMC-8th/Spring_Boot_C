package umc.spring.service.missionservice;

import umc.spring.web.dto.MissionRequest;
import umc.spring.web.dto.MissionResponse;

public interface MissionCommandService {
    MissionResponse.AcceptMissionResultDTO acceptMission(MissionRequest.AcceptMissionDTO request, Long memberId);
}
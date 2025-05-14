package umc.spring.service.MissionService;

import umc.spring.domain.mapping.UserMission;

public interface MissionCommandService {
    UserMission challengeMission(Long missionId);
}

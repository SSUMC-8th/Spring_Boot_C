package umc.spring.service.MissionService;

import umc.spring.domain.mapping.UserMission;

import java.util.Optional;

public interface MissionQueryService {
    Optional<UserMission> findUserMissionByUserIdAndMissionId(Long userId,Long missionId);
}

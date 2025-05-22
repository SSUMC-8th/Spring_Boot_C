package umc.study.service.UserMissionService;

import org.springframework.data.domain.Page;
import umc.study.domain.mapping.UserMission;

public interface UserMissionQueryService {
    Page<UserMission> getMyOngoingMissions(Long userId, int page);
}

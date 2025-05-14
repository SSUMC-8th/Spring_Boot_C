package umc.spring.service.MemberMissionService;

import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionCommandService {
    @Transactional
    MemberMission startMission(Long memberId, Long missionId);
}

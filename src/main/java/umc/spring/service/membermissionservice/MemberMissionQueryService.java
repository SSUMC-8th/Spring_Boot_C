package umc.spring.service.membermissionservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionQueryService {

    Page<MemberMission> getInProgressMissions(Long memberId, Pageable pageable);
}

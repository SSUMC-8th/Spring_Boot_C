package umc.spring.service.MemberMissionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionQueryService {
    Page<MemberMission> getMyMissions(Long memberId, int pageable);

}

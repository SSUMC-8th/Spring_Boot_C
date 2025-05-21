package umc.spring.repository.membermissionrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.dto.MissionProgressDto;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>{

    //List<MissionProgressDto> findInProgressMissions(Long memberId, String cursorValue, int limit);
}

package umc.spring.repository.membermissionrepository;

import umc.spring.dto.MissionProgressDto;

import java.util.List;

public interface MemberMissionRepositoryCustom {

    List<MissionProgressDto> findInProgressMissions(Long memberId, String cursorValue, int limit);

}

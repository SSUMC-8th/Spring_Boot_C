package umc.spring.repository.memberrepository;

import umc.spring.dto.MemberDto;
import umc.spring.dto.MissionCursorResponseDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MissionCursorResponseDto> findInProgressMissionsByCursor(Long memberId, String cursorValue, int limit);
    MemberDto findMemberById(Long memberId);

}

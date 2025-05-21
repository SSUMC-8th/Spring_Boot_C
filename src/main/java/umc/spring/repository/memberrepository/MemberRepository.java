package umc.spring.repository.memberrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.dto.MemberDto;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //List<MissionCursorResponseDto> findInProgressMissionsByCursor(Long memberId, String cursorValue, int limit);

    MemberDto findMemberById(Long memberId);
}

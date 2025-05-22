package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.dto.MemberDTO;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //List<MissionCursorResponseDto> findInProgressMissionsByCursor(Long memberId, String cursorValue, int limit);

    MemberDTO findMemberById(Long memberId);
}

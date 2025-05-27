package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //List<MissionCursorResponseDto> findInProgressMissionsByCursor(Long memberId, String cursorValue, int limit);

    Optional<Member> findByEmail(String email);
}

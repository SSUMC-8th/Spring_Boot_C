package umc.spring.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.User;

public interface MemberRepository extends JpaRepository<User, Long> {
}

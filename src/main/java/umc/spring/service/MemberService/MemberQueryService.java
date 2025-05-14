package umc.spring.service.MemberService;

import umc.spring.domain.User;

import java.util.Optional;

public interface MemberQueryService {

    Optional<User> findMember(Long id);
}

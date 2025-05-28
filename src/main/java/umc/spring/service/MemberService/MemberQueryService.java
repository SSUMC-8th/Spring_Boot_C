package umc.spring.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import umc.spring.domain.User;
import umc.spring.web.dto.MemberResponseDTO;

import java.util.Optional;

public interface MemberQueryService {

    Optional<User> findMember(Long id);
    Optional<User> findAnyMember();
    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}

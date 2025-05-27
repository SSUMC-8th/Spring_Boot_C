package umc.spring.service.MemberService;

import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.domain.Member;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}

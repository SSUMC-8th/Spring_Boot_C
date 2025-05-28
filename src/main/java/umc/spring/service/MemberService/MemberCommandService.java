package umc.spring.service.MemberService;

import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.domain.Member;
import umc.spring.web.dto.MemberResponseDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}

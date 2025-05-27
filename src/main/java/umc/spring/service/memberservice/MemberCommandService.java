package umc.spring.service.memberservice;

import umc.spring.domain.Member;
import umc.spring.dto.web.MemberRequestDTO;
import umc.spring.dto.web.MemberResponseDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDTO request);

    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}

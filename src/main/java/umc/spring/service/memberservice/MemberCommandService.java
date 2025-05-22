package umc.spring.service.memberservice;

import umc.spring.domain.Member;
import umc.spring.dto.web.MemberRequestDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDTO request);
}

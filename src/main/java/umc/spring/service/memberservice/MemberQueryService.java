package umc.spring.service.memberservice;

import jakarta.servlet.http.HttpServletRequest;
import umc.spring.dto.web.MemberResponseDTO;

public interface MemberQueryService {

    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}

package umc.study.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import umc.study.web.dto.responseDTO.UserResponseDTO;

public interface UserQueryService {

    UserResponseDTO.UserInfoDTO getUserInfo(HttpServletRequest request);
}

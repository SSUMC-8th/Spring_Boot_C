package umc.study.service.UserService;

import umc.study.domain.User;
import umc.study.web.dto.requestDTO.UserRequestDTO;

public interface UserCommandService {

    User joinUser(UserRequestDTO.JoinDto request);
}

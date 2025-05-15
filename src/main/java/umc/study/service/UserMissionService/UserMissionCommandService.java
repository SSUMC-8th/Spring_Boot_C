package umc.study.service.UserMissionService;

import umc.study.web.dto.requestDTO.UserMissionRequestDTO;
import umc.study.web.dto.responseDTO.UserMissionResponseDTO;

public interface UserMissionCommandService {
    UserMissionResponseDTO.ChallengeResult challenge(UserMissionRequestDTO.ChallengeRequest dto);
}

package umc.study.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.converter.UserMissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.requestDTO.UserMissionRequestDTO;
import umc.study.web.dto.responseDTO.UserMissionResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class UserMissionCommandServiceImpl implements UserMissionCommandService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserMissionConverter userMissionConverter;

    @Override
    public UserMissionResponseDTO.ChallengeResult challenge(UserMissionRequestDTO.ChallengeRequest dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Mission mission = missionRepository.findById(dto.getMissionId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        UserMission userMission = userMissionConverter.toEntity(user, mission, dto);
        userMissionRepository.save(userMission);

        return userMissionConverter.toResponse(userMission);
    }
}


package umc.study.service.UserMissionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.TempHandler;
import umc.study.domain.User;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.repository.UserRepository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserMissionQueryServiceImpl implements UserMissionQueryService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public Page<UserMission> getMyOngoingMissions(Long userId, int page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.USER_NOT_FOUND));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        return userMissionRepository.findAllByUserAndStatus(user, MissionStatus.CHALLENGING, pageable);
    }
}

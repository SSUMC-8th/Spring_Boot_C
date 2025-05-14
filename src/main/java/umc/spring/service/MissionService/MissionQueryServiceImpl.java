package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.MissionRepository.MemberMissionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<UserMission> findUserMissionByUserIdAndMissionId(Long userId,Long missionId) {
        return memberMissionRepository.findByUserIdAndMissionId(userId, missionId);
    }
}

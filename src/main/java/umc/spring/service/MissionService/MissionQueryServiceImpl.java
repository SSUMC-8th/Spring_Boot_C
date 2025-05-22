package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.enums.MissionStatus;
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

    @Override
    public Page<UserMission> findAllMissions(Integer page) {
        Page<UserMission> MemberMissionPage = memberMissionRepository.findAllByStatus(MissionStatus.IN_PROGRESS,PageRequest.of(page, 10));
        return MemberMissionPage;
    }
}

package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.MissionRepository.MemberMissionRepository;
import umc.spring.repository.MissionRepository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    public UserMission challengeMission(Long missionId) {

        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new RuntimeException("미션이 존재하지 않습니다."));;
        User user = memberRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();

        return memberMissionRepository.save(userMission);
    }
}

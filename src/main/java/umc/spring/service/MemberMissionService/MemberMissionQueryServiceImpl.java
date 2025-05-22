package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<MemberMission> getMyMissions(Long memberId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return memberMissionRepository.findAllByMemberIdAndStatus(memberId, MissionStatus.IN_PROGRESS, pageable);
    }
}


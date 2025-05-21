package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.MissionNotFoundException;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissoinRepository.MissionRepository;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public MemberMission startMission(Long memberId, Long missionId) {
        Member member = memberRepository.getReferenceById(memberId);
        Mission mission = missionRepository.getReferenceById(missionId);

        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);
        return memberMissionRepository.save(memberMission);
    }

    @Transactional
    @Override
    public void completeMission(Long memberId, Long missionId) {
        MemberMission memberMission = memberMissionRepository
                .findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new MissionNotFoundException("해당 미션 정보가 없습니다."));

        if (memberMission.getStatus() != MissionStatus.IN_PROGRESS) {
            throw new IllegalStateException("진행 중인 미션이 아닙니다.");
        }

        memberMission.updateStatus(MissionStatus.COMPLETED);
    }

}

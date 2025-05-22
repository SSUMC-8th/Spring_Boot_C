package umc.spring.service.missionservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionValidationServiceImpl implements MissionValidationService{

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    public boolean isAlreadyAccepted(Long memberId, Long missionId) {
        // 회원과 미션이 존재하는지 먼저 확인
        if (!existsMemberAndMission(memberId, missionId)) {
            return false; // 존재하지 않으면 중복이 아님
        }

        // 회원 조회
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return false;
        }

        // 이미 도전 중인 미션인지 확인
        return member.getMemberMissionList().stream()
                .anyMatch(mm -> mm.getMission().getId().equals(missionId));
    }

    @Override
    public boolean existsMemberAndMission(Long memberId, Long missionId) {
        if (memberId == null || missionId == null) {
            return false;
        }

        boolean memberExists = memberRepository.existsById(memberId);
        boolean missionExists = missionRepository.existsById(missionId);

        return memberExists && missionExists;
    }
}

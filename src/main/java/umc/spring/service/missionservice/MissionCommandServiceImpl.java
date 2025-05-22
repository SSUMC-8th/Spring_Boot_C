package umc.spring.service.missionservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionResponseDTO.AcceptMissionResultDTO acceptMission(MissionRequestDTO.AcceptMissionDTO request, Long memberId) {
        // 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // 미션 조회
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        // 미션 기간이 만료되었는지 확인
        if (mission.getDuration().isBefore(LocalDateTime.now())) {
            throw new MissionHandler(ErrorStatus.MISSION_EXPIRED);
        }

        // 이미 도전 중인 미션인지 확인
        boolean alreadyAccepted = member.getMemberMissionList().stream()
                .anyMatch(mm -> mm.getMission().getId().equals(mission.getId()));

        if (alreadyAccepted) {
            throw new MissionHandler(ErrorStatus.MISSION_ALREADY_ACCEPTED);
        }

        // 새로운 MemberMission 객체 생성 및 저장
        MemberMission memberMission = MemberMissionConverter.toMemberMission(mission, member);
        memberMission = memberMissionRepository.save(memberMission);

        // 회원의 미션 목록에 추가
        member.getMemberMissionList().add(memberMission);

        return MemberMissionConverter.toAcceptMissionResultDTO(memberMission);
    }
}
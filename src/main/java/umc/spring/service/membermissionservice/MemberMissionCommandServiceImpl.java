package umc.spring.service.membermissionservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberMissionHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.dto.web.MissionRequestDTO;
import umc.spring.dto.web.MissionResponseDTO;
import umc.spring.repository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionResponseDTO.CompleteMissionResultDTO completeMission(MissionRequestDTO.CompleteMissionDTO request) {
        // 회원 미션 조회
        MemberMission memberMission = memberMissionRepository.findById(request.getMemberMissionId())
                .orElseThrow(() -> new MemberMissionHandler(ErrorStatus.MEMBER_MISSION_NOT_FOUND));

        // 해당 회원의 미션인지 확인
        if (!memberMission.getMember().getId().equals(request.getMemberId())) {
            throw new MemberMissionHandler(ErrorStatus.MEMBER_MISSION_NOT_FOUND);
        }

        // 진행중인 미션인지 확인
        if (memberMission.getStatus() != MissionStatus.IN_PROGRESS) {
            throw new MissionHandler(ErrorStatus.MISSION_NOT_IN_PROGRESS);
        }

        try {
            // 도메인 메서드를 통한 미션 완료 처리
            memberMission.completeMission();

            // 회원에게 포인트 지급
            Member member = memberMission.getMember();
            Long missionPoint = memberMission.getMission().getPoint();
            member.addPoint(missionPoint);

            // 변경사항 저장 (dirty checking에 의해 자동 저장됨)

            return MemberMissionConverter.toCompleteMissionResultDTO(memberMission);

        } catch (IllegalStateException e) {
            // 도메인에서 발생한 예외를 비즈니스 예외로 변환
            if (e.getMessage().contains("진행중인 미션")) {
                throw new MissionHandler(ErrorStatus.MISSION_NOT_IN_PROGRESS);
            } else if (e.getMessage().contains("기간이 만료")) {
                throw new MissionHandler(ErrorStatus.MISSION_EXPIRED);
            }
            throw new MissionHandler(ErrorStatus.MISSION_COMPLETION_FAILED);
        }
    }
}
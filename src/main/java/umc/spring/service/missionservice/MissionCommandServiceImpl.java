package umc.spring.service.missionservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
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
    private final StoreRepository storeRepository;

    @Override
    public MissionResponseDTO.CreateMissionResultDTO createMission(MissionRequestDTO.CreateMissionDTO request) {
        // 가게 조회 (어노테이션에서 이미 존재 여부 검증됨)
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(request, store);

        // 미션 저장
        mission = missionRepository.save(mission);

        return MissionConverter.toCreateMissionResultDTO(mission);
    }

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

        // 새로운 MemberMission 객체 생성 및 저장
        MemberMission memberMission = MemberMissionConverter.toMemberMission(mission, member);
        memberMission = memberMissionRepository.save(memberMission);

        // 회원의 미션 목록에 추가
        member.getMemberMissionList().add(memberMission);

        return MemberMissionConverter.toAcceptMissionResultDTO(memberMission);
    }
}
package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.MemberMissionDTO toMemberMissionDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        Store store = mission.getStore();

        LocalDateTime deadline = mission.getDuration();
        LocalDateTime now = LocalDateTime.now();
        Period period = Period.between(deadline.toLocalDate(), now.toLocalDate());

        // 남은 일수 계산
        long daysRemaining = period.getDays();
        if (daysRemaining < 0) daysRemaining = 0;

        // 만료 여부 확인
        boolean isExpired = deadline.isBefore(now);

        return MemberMissionResponseDTO.MemberMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .storeName(store.getName())
                .storeId(store.getId())
                .missionContent(mission.getContent())
                .missionPoint(mission.getPoint())
                .status(memberMission.getStatus())
                .isReviewed(memberMission.getIsReviewed())
                .deadline(deadline)
                .startedAt(memberMission.getCreatedAt())
                .daysRemaining(daysRemaining)
                .isExpired(isExpired)
                .build();
    }

    public static MemberMissionResponseDTO.MemberMissionListDTO toMemberMissionListDTO(Page<MemberMission> memberMissionPage) {
        return MemberMissionResponseDTO.MemberMissionListDTO.builder()
                .memberMissionList(memberMissionPage.getContent().stream()
                        .map(MemberMissionConverter::toMemberMissionDTO)
                        .collect(Collectors.toList()))
                .totalPages(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }

    // 도전 중인 미션으로 등록
    public static MemberMission toMemberMission(Mission mission, Member member) {
        return MemberMission.builder()
                .mission(mission)
                .member(member)
                .status(MissionStatus.IN_PROGRESS)
                .isReviewed(false)
                .authenticationNum(0) // 인증 횟수 초기화
                .build();
    }

    // 응답 DTO로 변환
    public static MissionResponseDTO.AcceptMissionResultDTO toAcceptMissionResultDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        return MissionResponseDTO.AcceptMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionContent(mission.getContent())
                .point(mission.getPoint())
                .acceptedAt(memberMission.getCreatedAt())
                .deadline(mission.getDuration())
                .build();
    }
}

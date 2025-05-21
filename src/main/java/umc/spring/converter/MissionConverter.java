package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionResponseDTO;

public class MissionConverter {

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
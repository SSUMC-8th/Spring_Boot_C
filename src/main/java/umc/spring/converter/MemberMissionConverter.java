package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)  // 진행중 상태
                .ownerId(member.getId())        // 주인 ID (회원 ID 저장)
                .build();
    }
}

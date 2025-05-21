package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)  // 진행중 상태
                .ownerId(member.getId())        // 주인 ID (회원 ID 저장)
                .build();
    }

    public static MissionResponseDTO.MemberMissionResponseDTO.PageDTO toPageDTO(Page<MemberMission> page) {
        List<MissionResponseDTO.MemberMissionResponseDTO> missions = page.getContent().stream()
                .map(m -> MissionResponseDTO.MemberMissionResponseDTO.builder()
                        .missionId(m.getMission().getId())
                        .missionTitle(m.getMission().getMissionContent())
                        .daysLeft(Period.between(LocalDate.now(), m.getMission().getPeriod()).getDays())
                        .build())
                .toList();

        return MissionResponseDTO.MemberMissionResponseDTO.PageDTO.builder()
                .missions(missions)
                .hasNext(page.hasNext())
                .build();
    }
}

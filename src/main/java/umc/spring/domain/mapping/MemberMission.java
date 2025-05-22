package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    private Boolean isReviewed;

    private Integer authenticationNum;

    // 미션 완료 처리 메서드
    public void completeMission() {
        if (this.status != MissionStatus.IN_PROGRESS) {
            throw new MissionHandler(ErrorStatus.MISSION_NOT_IN_PROGRESS);
        }

        // 미션 기간이 지났는지 확인
        if (this.mission.getDuration().isBefore(LocalDateTime.now())) {
            throw new MissionHandler(ErrorStatus.MISSION_EXPIRED);
        }

        this.status = MissionStatus.COMPLETED;
    }

    public void updateReviewStatus(Boolean isReviewed) {
        this.isReviewed = isReviewed;
    }
}

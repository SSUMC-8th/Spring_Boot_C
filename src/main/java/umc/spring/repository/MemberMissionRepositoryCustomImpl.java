package umc.spring.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMission;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;
import umc.spring.dto.MissionProgressDto;

import java.util.List;

/**
 * SELECT
 *     mm.id AS member_mission_id,
 *     m.point AS mission_point,
 *     mm.status AS mission_status,
 *     s.name AS store_name,
 *     m.content AS mission_content,
 *     mm.isReviewed AS mission_isReviewed,
 *     mm.created_at AS started_at,
 *     CONCAT(LPAD(m.point,10,'0'), LPAD(m.id,10,'0')) as cursor_value
 * FROM member_mission mm
 * JOIN mission m ON mm.mission_id = m.id
 * JOIN store s ON m.store_id = s.id
 * WHERE
 *     mm.member_id = :member_id
 *     AND mm.status = 'IN_PROGRESS'
 *     AND CONCAT(LPAD(m.point,10,'0'), LPAD(m.id,10,'0')) < :cursor_value
 * ORDER BY
 *     mm.point DESC, m.id DESC
 * LIMIT 15;
 */

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryCustomImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // Q 클래스 인스턴스
    QMemberMission memberMission = QMemberMission.memberMission;
    QMission mission = QMission.mission;
    QStore store = QStore.store;

    @Override
    public List<MissionProgressDto> findInProgressMissions(Long memberId, String cursorValue, int limit) {
        return queryFactory
                .select(Projections.constructor(MissionProgressDto.class,
                        memberMission.id.as("memberMissionId"),
                        mission.point.as("missionPoint"),
                        memberMission.status.as("missionStatus"),
                        store.name.as("storeName"),
                        mission.content.as("missionContent"),
                        memberMission.isReviewed.as("missionIsReviewed"),
                        memberMission.createdAt.as("startedAt"),
                        createCursorValue().as("cursorValue")
                ))
                .from(memberMission)
                .join(memberMission.mission(), mission)
                .join(mission.store(), store)
                .where(createPredicate(memberId, cursorValue))
                .orderBy(mission.point.desc(), mission.id.desc())
                .limit(limit)
                .fetch();
    }

    /**
     * 커서 값 생성 (포인트와 미션 ID의 조합)
     */
    private StringExpression createCursorValue() {
        StringExpression pointStr = Expressions.stringTemplate("LPAD({0}, 10, '0')", mission.point);
        StringExpression idStr = Expressions.stringTemplate("LPAD({0}, 10, '0')", mission.id);

        return Expressions.stringTemplate("CONCAT({0}, {1})", pointStr, idStr);
    }

    /**
     * Predicate를 사용한 조건 생성 - BooleanBuilder 활용
     */
    private Predicate createPredicate(Long memberId, String cursorValue) {
        // BooleanBuilder는 Predicate의 구현체
        BooleanBuilder builder = new BooleanBuilder();

        // 필수 조건 추가
        builder.and(memberMission.member().id.eq(memberId));
        builder.and(memberMission.status.eq(MissionStatus.IN_PROGRESS));

        // 커서 값이 있는 경우 추가 조건 적용
        if (cursorValue != null && !cursorValue.isBlank()) {
            builder.and(createCursorValue().lt(cursorValue));
        }

        return builder;
    }
}

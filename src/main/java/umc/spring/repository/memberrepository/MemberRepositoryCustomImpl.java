package umc.spring.repository.memberrepository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QFood;
import umc.spring.domain.QMember;
import umc.spring.domain.QMission;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;
import umc.spring.dto.MemberDto;
import umc.spring.dto.MissionCursorResponseDto;

import java.util.List;

/**
 * SELECT
 *     m.address,
 *     m.point,
 *     (SELECT COUNT(*)
 *      FROM member_mission mm2
 *      WHERE mm2.user_id = :memberId AND mm2.status = 'COMPLETED') AS completedMissionCount,
 *     DATEDIFF(mission.duration, CURRENT_DATE) AS daysRemaining,
 *     s.name AS storeName,
 *     f.type AS foodType,
 *     mission.content,
 *     mission.point,
 *     CONCAT(LPAD(DATEDIFF(mission.duration, CURRENT_DATE), 10, '0'),
 *            LPAD(CAST(mission.id AS char), 10, '0')) AS cursor
 * FROM member_mission mm
 * JOIN member m ON mm.user_id = m.id
 * JOIN mission ON mm.mission_id = mission.id
 * JOIN store s ON mission.store_id = s.id
 * JOIN food f ON s.food_id = f.id
 * WHERE
 *     mm.user_id = :memberId
 *     AND mm.status = 'IN_PROGRESS'
 *     AND mission.duration >= CURRENT_DATE
 *     AND (
 *         :cursorValue IS NULL
 *         OR CONCAT(LPAD(DATEDIFF(mission.duration, CURRENT_DATE), 10, '0'),
 *                  LPAD(CAST(mission.id AS char), 10, '0')) < :cursorValue
 *     )
 * ORDER BY
 *     DATEDIFF(mission.duration, CURRENT_DATE) DESC,
 *     mission.id DESC
 * LIMIT :limit;
 */

@RequiredArgsConstructor
@Repository
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MissionCursorResponseDto> findInProgressMissionsByCursor(Long memberId, String cursorValue, int limit) {
        QMemberMission memberMission = QMemberMission.memberMission;
        QMemberMission memberMission2 = new QMemberMission("memberMission2");
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QFood food = QFood.food;
        QMember member = QMember.member;

        // 날짜 차이를 계산하는 표현식 (DB 호환성 개선)
        NumberExpression<Long> daysRemainingExpr = Expressions.numberTemplate(Long.class,
                "DATEDIFF({0}, CURRENT_DATE())",
                mission.duration);

        // 커서 값 생성
        StringExpression cursorExpr = Expressions.stringTemplate(
                "CONCAT(LPAD(DATEDIFF({0}, CURRENT_DATE()), 10, '0'), LPAD(CAST({1} AS char), 10, '0'))",
                mission.duration, mission.id);

        // 날짜 비교 조건
        NumberExpression<Integer> dateDiff = Expressions.numberTemplate(Integer.class,
                "DATEDIFF({0}, CURRENT_DATE())",
                mission.duration
        );

        return queryFactory
                .select(Projections.constructor(MissionCursorResponseDto.class,
                        member.address,
                        member.point,
                        JPAExpressions
                                .select(memberMission2.count())
                                .from(memberMission2)
                                .where(memberMission2.member().id.eq(memberId),
                                        memberMission2.status.eq(MissionStatus.COMPLETED)),
                        Expressions.asNumber(daysRemainingExpr).castToNum(Long.class),
                        store.name,
                        food.type,
                        mission.content,
                        mission.point,
                        cursorExpr
                ))
                .from(memberMission)
                .join(memberMission.member(), member)
                .join(memberMission.mission(), mission)
                .join(mission.store(), store)
                .join(store.food(), food)
                .where(
                        memberMission.member().id.eq(memberId),
                        memberMission.status.eq(MissionStatus.IN_PROGRESS),
                        dateDiff.goe(0),
                        cursorCondition(cursorValue, cursorExpr)
                )
                .orderBy(daysRemainingExpr.desc(), mission.id.desc())
                .limit(limit)
                .fetch();
    }

    private BooleanExpression cursorCondition(String cursorValue, StringExpression cursorExpr) {
        return cursorValue == null ? null : cursorExpr.lt(cursorValue);
    }


    /**
     * SELECT id, name, email, phone_num, point FROM member WHERE id = 12
     */
    @Override
    public MemberDto findMemberById(Long memberId) {
        QMember member = QMember.member;

        return queryFactory
                .select(Projections.constructor(MemberDto.class,
                        member.id,
                        member.name,
                        member.email,
                        member.phoneNum,
                        member.point))
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}

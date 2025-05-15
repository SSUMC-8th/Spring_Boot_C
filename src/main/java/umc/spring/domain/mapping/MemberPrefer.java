package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Food;
import umc.spring.domain.Member;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.FoodCategory;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;

    public static MemberPrefer createMemberPrefer(Member member, FoodCategory foodCategory) {
        MemberPrefer memberPrefer = MemberPrefer.builder()
                .foodCategory(foodCategory)
                .member(member)
                .build();

        // 양방향 관계 설정 (Member 쪽에도 이 MemberPrefer 추가)
        member.getMemberPreferList().add(memberPrefer);

        return memberPrefer;
    }

}
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
        return MemberPrefer.builder()
                .member(member)
                .foodCategory(foodCategory)
                .build();
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getMemberPreferList().remove(this);
        }
        this.member = member;
        if (member != null && !member.getMemberPreferList().contains(this)) {
            member.getMemberPreferList().add(this);
        }
    }

}
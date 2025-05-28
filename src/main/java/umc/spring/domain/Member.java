package umc.spring.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.FoodCategory;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MemberStatus;
import umc.spring.domain.enums.Role;
import umc.spring.domain.mapping.MemberAgree;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 20, nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    @Past
    private LocalDate birthDate;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String specAddress;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    @ColumnDefault("'ACTIVE'")
    private MemberStatus status;

    private LocalDateTime inactiveDate;

    @Email(message = "이메일 형식이 올바르지 않습니다")
    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 20)
    @Nullable
    private String phoneNum;

    @ColumnDefault("0")
    private Long point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Notification> notificationList = new ArrayList<>();

    public void encodePassword(String password) {
        this.password = password;
    }

    public void addFoodPreferences(List<FoodCategory> foodCategories) {
        // 기존 선호도 제거
        this.memberPreferList.clear();

        // 새로운 선호도 추가
        if (foodCategories != null && !foodCategories.isEmpty()) {
            foodCategories.forEach(category -> {
                MemberPrefer memberPrefer = MemberPrefer.createMemberPrefer(this, category);
                this.memberPreferList.add(memberPrefer);
            });
        }
    }

    public void addPoint(Long additionalPoints) {
        if (this.point == null) {
            this.point = 0L;
        }
        this.point += additionalPoints;
    }

}
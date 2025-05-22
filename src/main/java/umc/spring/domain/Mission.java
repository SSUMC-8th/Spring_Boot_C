package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.MemberMission;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATETIME(6)")
    private LocalDateTime duration;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    private Long point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();

    public void setStore(Store store) {
        // 기존 연관관계 제거
        if (this.store != null) {
            this.store.getMissionList().remove(this);
        }

        // 새로운 연관관계 설정
        this.store = store;
        if (store != null && !store.getMissionList().contains(this)) {
            store.getMissionList().add(this);
        }
    }
}

package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.FoodList;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodList category;

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StorePhoto> storePhotoList = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreHours> storeHoursList = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

}

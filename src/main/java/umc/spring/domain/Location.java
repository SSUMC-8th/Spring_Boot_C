package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Store> storeList = new ArrayList<>();
}

package umc.spring.repository.MissoinRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission,Long> {
    Slice<Mission> findAllByStoreId(Long storeId, Pageable pageable);
}

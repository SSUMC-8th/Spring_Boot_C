package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 가게의 미션 목록을 페이징하여 조회
    Page<Mission> findAllByStore(Store store, Pageable pageable);

    // 특정 가게의 미션 목록을 생성일 기준 내림차순으로 페이징 조회
    Page<Mission> findAllByStoreOrderByCreatedAtDesc(Store store, Pageable pageable);
}

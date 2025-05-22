package umc.spring.service.missionservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;

public interface MissionQueryService {

    // 특정 가게의 미션 목록 조회
    Page<Mission> getMissionsByStore(Long storeId, Pageable pageable);

}

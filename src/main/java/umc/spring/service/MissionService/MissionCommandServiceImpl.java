package umc.spring.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MissoinRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;


    @Override
    @Transactional
    public Mission createMission(MissionRequestDTO.MissionCreateDTO dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Mission mission = MissionConverter.toMission(dto, store);
        return missionRepository.save(mission);
    }

    @Override
    public Mission joinMission(MissionRequestDTO.MissionCreateDTO missionRequestDTO) {
        return null;
    }
}

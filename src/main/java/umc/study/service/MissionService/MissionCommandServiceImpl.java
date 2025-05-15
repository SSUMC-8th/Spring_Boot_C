package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.requestDTO.MissionRequestDTO;
import umc.study.web.dto.responseDTO.MissionResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MissionConverter missionConverter;

    @Override
    public MissionResponseDTO.CreateResult createMission(MissionRequestDTO.CreateMission dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = missionConverter.toEntity(dto, store);
        missionRepository.save(mission);

        return missionConverter.toResponse(mission);
    }
}


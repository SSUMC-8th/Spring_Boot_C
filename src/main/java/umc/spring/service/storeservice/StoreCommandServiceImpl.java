package umc.spring.service.storeservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodHandler;
import umc.spring.apiPayload.exception.handler.LocationHandler;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Food;
import umc.spring.domain.Location;
import umc.spring.domain.Store;
import umc.spring.domain.StoreOpeningHours;
import umc.spring.repository.FoodRepository;
import umc.spring.repository.LocationRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final FoodRepository foodRepository;
    private final LocationRepository locationRepository;

    @Override
    public StoreResponseDTO.CreateStoreResultDTO createStore(StoreRequestDTO.CreateStoreDTO request) {
        // 1. Food 엔티티 조회
        Food food = foodRepository.findById(request.getFoodId())
                .orElseThrow(() -> new FoodHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));

        // 2. Location 엔티티 조회
        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new LocationHandler(ErrorStatus.LOCATION_NOT_FOUND));

        // 3. Store 엔티티 생성
        Store store = StoreConverter.toStoreDTO(request, food, location);

        // 4. Store 저장
        Store savedStore = storeRepository.save(store);

        // 5. 영업시간 추가
        List<StoreOpeningHours> openingHoursList = request.getOpeningHours().stream()
                .map(openingHoursDTO -> StoreConverter.toStoreOpeningHoursDTO(openingHoursDTO, savedStore))
                .collect(Collectors.toList());

        // 6. 영업시간 설정
        savedStore.getOpeningHourList().addAll(openingHoursList);

        return StoreConverter.toCreateStoreResultDTO(savedStore.getId());
    }
}
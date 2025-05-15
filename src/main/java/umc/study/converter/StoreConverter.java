package umc.study.converter;

import org.springframework.stereotype.Component;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.web.dto.requestDTO.StoreCreateRequestDTO;
import umc.study.web.dto.responseDTO.StoreResponseDTO;

import java.time.LocalDateTime;

@Component
public class StoreConverter {

    public Store toEntity(StoreCreateRequestDTO.InputStore request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .category(request.getStoreCategory())
                .region(region)
                .score(0.0f)
                .build();
    }

    public StoreResponseDTO.inputResultDTO toResponse(Store store) {
        return StoreResponseDTO.inputResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}

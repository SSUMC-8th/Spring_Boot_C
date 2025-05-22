package umc.spring.service.storeservice;

import umc.spring.dto.web.StoreRequestDTO;
import umc.spring.dto.web.StoreResponseDTO;

public interface StoreCommandService {

    StoreResponseDTO.CreateStoreResultDTO createStore(StoreRequestDTO.CreateStoreDTO request);
}

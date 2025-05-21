package umc.spring.service.storeservice;

import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

public interface StoreCommandService {

    StoreResponseDTO.CreateStoreResultDTO createStore(StoreRequestDTO.CreateStoreDTO request);
}

package umc.study.service.StoreService;

import umc.study.web.dto.requestDTO.StoreCreateRequestDTO;
import umc.study.web.dto.responseDTO.StoreResponseDTO;

public interface StoreCommandService {
    StoreResponseDTO.inputResultDTO createStore(StoreCreateRequestDTO.InputStore request);
}

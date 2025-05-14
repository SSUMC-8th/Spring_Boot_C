package umc.spring.service.storeservice;

import umc.spring.web.dto.StoreRequest;
import umc.spring.web.dto.StoreResponse;

public interface StoreCommandService {

    StoreResponse.CreateStoreResultDTO createStore(StoreRequest.CreateStoreDTO request);
}

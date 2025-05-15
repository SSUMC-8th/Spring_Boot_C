package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.StoreConverter;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.web.dto.requestDTO.StoreCreateRequestDTO;
import umc.study.web.dto.responseDTO.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreConverter storeConverter;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.inputResultDTO> createStore(@RequestBody @Valid StoreCreateRequestDTO.InputStore request) {
        StoreResponseDTO.inputResultDTO resultDTO = storeCommandService.createStore(request);
        return ApiResponse.onSuccess(resultDTO);

    }
}

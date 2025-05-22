package umc.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.BaseResponse;
import umc.spring.converter.TempConverter;
import umc.spring.service.tempservice.TempQueryService;
import umc.spring.dto.web.TempResponseDTO;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public BaseResponse<TempResponseDTO.TempTestDTO> testAPI(){

        return BaseResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public BaseResponse<TempResponseDTO.TempExceptionDTO> exceptionAPI(@RequestParam(name = "flag") Integer flag){
        tempQueryService.CheckFlag(flag);
        return BaseResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
}

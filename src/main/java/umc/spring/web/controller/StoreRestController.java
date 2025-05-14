package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.service.storeservice.StoreCommandService;
import umc.spring.web.dto.StoreRequest;
import umc.spring.web.dto.StoreResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
@Tag(name = "Store", description = "가게 관련 API")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @Operation(summary = "가게 생성", description = "특정 지역에 새로운 가게를 추가합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "가게 생성 성공",
                    content = @Content(
                            schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청",
                    content = @Content(
                            schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "음식 카테고리 또는 지역을 찾을 수 없음",
                    content = @Content(
                            schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "서버 에러",
                    content = @Content(
                            schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class)
                    )
            )
    })
    @PostMapping
    public umc.spring.apiPayload.ApiResponse<StoreResponse.CreateStoreResultDTO> createStore(
            @Parameter(
                    description = "가게 생성 정보",
                    required = true,
                    schema = @Schema(implementation = StoreRequest.CreateStoreDTO.class)
            )
            @Valid @RequestBody StoreRequest.CreateStoreDTO request) {
        return umc.spring.apiPayload.ApiResponse.onSuccess(storeCommandService.createStore(request));
    }
}
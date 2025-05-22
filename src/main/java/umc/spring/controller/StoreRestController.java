package umc.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.service.storeservice.StoreCommandService;
import umc.spring.service.storeservice.StoreQueryService;
import umc.spring.dto.web.StoreRequestDTO;
import umc.spring.dto.web.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
@Tag(name = "Store", description = "가게 관련 API")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@PathVariable(name = "storeId") Long storeId,@RequestParam(name = "page") Integer page){
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }


    @Operation(summary = "가게 생성", description = "특정 지역에 새로운 가게를 추가합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "가게 생성 성공",
                    content = @Content(
                            schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청",
                    content = @Content(
                            schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "음식 카테고리 또는 지역을 찾을 수 없음",
                    content = @Content(
                            schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "서버 에러",
                    content = @Content(
                            schema = @Schema(implementation = umc.spring.apiPayload.ApiResponse.class)
                    )
            )
    })
    @PostMapping
    public ApiResponse<StoreResponseDTO.CreateStoreResultDTO> createStore(
            @Parameter(
                    description = "가게 생성 정보",
                    required = true,
                    schema = @Schema(implementation = StoreRequestDTO.CreateStoreDTO.class)
            )
            @Valid @RequestBody StoreRequestDTO.CreateStoreDTO request) {
        return ApiResponse.onSuccess(storeCommandService.createStore(request));
    }
}
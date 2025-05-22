package umc.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.BaseResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.dto.web.StoreRequestDTO;
import umc.spring.dto.web.StoreResponseDTO;
import umc.spring.service.storeservice.StoreCommandService;
import umc.spring.service.storeservice.StoreQueryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
@Tag(name = "Store", description = "가게 관련 API")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public BaseResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return BaseResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }


    @Operation(summary = "가게 생성", description = "특정 지역에 새로운 가게를 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "가게 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "음식 카테고리 또는 지역을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PostMapping
    public BaseResponse<StoreResponseDTO.CreateStoreResultDTO> createStore(
            @Parameter(
                    description = "가게 생성 정보",
                    required = true,
                    schema = @Schema(implementation = StoreRequestDTO.CreateStoreDTO.class)
            )
            @Valid @RequestBody StoreRequestDTO.CreateStoreDTO request) {
        return BaseResponse.onSuccess(storeCommandService.createStore(request));
    }
}
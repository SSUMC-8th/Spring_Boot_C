package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreQueryService storeQueryService;

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @ValidPage @RequestParam(name = "page") Integer page) {

        var reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API",
            description = "특정 가게가 가진 미션들의 목록을 페이징 형식으로 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page가 1보다 작거나 잘못됨", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게 ID (PathVariable)"),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getMissionList(
            @ExistStore @PathVariable Long storeId,
            @ValidPage @RequestParam(name = "page") Integer page) {

        Pageable pageable = PageRequest.of(page-1, 10);
        Slice<Mission> missionList = storeQueryService.getMissions(storeId, pageable);
        return ApiResponse.onSuccess(StoreConverter.toMissionPreviewListDTO(missionList));
    }

}
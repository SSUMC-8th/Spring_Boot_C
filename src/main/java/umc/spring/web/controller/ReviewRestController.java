package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}")
    @Operation(
            summary = "가게 리뷰 작성 API",
            description = "특정 storeId에 대해 리뷰를 등록합니다. request body로 rating과 content를 받아 처리합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 등록 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 가게",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "001", description = "유효하지 않은 입력",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 ID입니다 (PathVariable)", required = true)
    })
    public ApiResponse<ReviewResponseDTO.ReviewResultDTO> createReview(
            @ExistStore @PathVariable Long storeId,
            @Valid @RequestBody ReviewRequestDTO.ReviewDTO request) {

        Review review = reviewCommandService.joinReview(storeId, request);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResultDTO(review));
    }
}
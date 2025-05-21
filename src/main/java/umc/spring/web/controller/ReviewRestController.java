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
import org.springframework.web.bind.annotation.*;
import umc.spring.service.reviewservice.ReviewCommandService;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @Operation(summary = "리뷰 생성", description = "특정 가게에 새로운 리뷰를 추가합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "리뷰 생성 성공",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "가게 또는 회원을 찾을 수 없음",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            )
    })
    @PostMapping
    public umc.spring.apiPayload.ApiResponse<ReviewResponseDTO.CreateReviewResultDTO> createReview(
            @Parameter(description = "리뷰 생성 정보", required = true)
            @Valid @RequestBody ReviewRequestDTO.CreateReviewDTO request,
            @Parameter(description = "회원 ID", required = true)
            @RequestParam Long memberId) {
        return umc.spring.apiPayload.ApiResponse.onSuccess(reviewCommandService.createReview(request, memberId));
    }
}
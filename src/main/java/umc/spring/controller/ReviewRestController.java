package umc.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.BaseResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.dto.web.ReviewRequestDTO;
import umc.spring.dto.web.ReviewResponseDTO;
import umc.spring.service.reviewservice.ReviewCommandService;
import umc.spring.service.reviewservice.ReviewQueryService;
import umc.spring.validation.annotation.OneIndexedPage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
@Validated
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    private final static int PAGE_SIZE = 10;

    @Operation(summary = "리뷰 생성", description = "특정 가게에 새로운 리뷰를 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "리뷰 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "가게 또는 회원을 찾을 수 없음")
    })
    @PostMapping
    public BaseResponse<ReviewResponseDTO.CreateReviewResultDTO> createReview(
            @Parameter(description = "리뷰 생성 정보", required = true)
            @Valid @RequestBody ReviewRequestDTO.CreateReviewDTO request,
            @Parameter(description = "회원 ID", required = true)
            @RequestParam Long memberId) {
        return BaseResponse.onSuccess(reviewCommandService.createReview(request, memberId));
    }

    @GetMapping("/{storeId}/my")
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "특정 가게에 내가 작성한 리뷰 목록을 페이징하여 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "가게 또는 회원을 찾을 수 없음")
    })
    public BaseResponse<ReviewResponseDTO.ReviewListDTO> getMyReviews(
            @Parameter(description = "가게 ID", required = true) @PathVariable Long storeId,
            @Parameter(description = "회원 ID", required = true) @RequestParam Long memberId,
            @Parameter(description = "페이지 번호 (1부터 시작)", required = true) @OneIndexedPage Integer page) {

        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);
        Page<Review> reviewPage = reviewQueryService.getMyReviewsForStore(storeId, memberId, pageRequest);

        return BaseResponse.onSuccess(ReviewConverter.toReviewListDTO(reviewPage));
    }
}
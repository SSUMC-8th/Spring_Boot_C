package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.web.dto.requestDTO.ReviewRequestDTO;
import umc.study.web.dto.responseDTO.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Tag(name = "review-rest-controller", description = "리뷰 관련 API")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping
    @Operation(summary = "리뷰 작성 API")
    public ApiResponse<ReviewResponseDTO.CreateResult> createReview(
            @RequestBody @Valid ReviewRequestDTO.CreateReview request) {
        return ApiResponse.onSuccess(reviewCommandService.createReview(request));
    }
}


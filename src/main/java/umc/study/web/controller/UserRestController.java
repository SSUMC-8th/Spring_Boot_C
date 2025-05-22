package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.converter.UserConverter;
import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.service.ReviewService.ReviewQueryService;
import umc.study.service.UserService.UserCommandService;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.requestDTO.UserRequestDTO;
import umc.study.web.dto.responseDTO.UserResponseDTO;
import umc.study.web.dto.reviewDTO.MyReviewListDTO;

import java.lang.reflect.Member;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;
    private final ReviewQueryService reviewQueryService;

    private final UserRepository userRepository;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request){
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @GetMapping("/me")
    @Operation(summary = "내가 작성한 리뷰 목록", description = "로그인한 사용자가 작성한 리뷰 목록을 페이지 단위로 조회합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "사용자 ID", required = true),
            @Parameter(name = "page", description = "1부터 시작하는 페이지 번호입니다.", required = true)
    })
    public ApiResponse<MyReviewListDTO> getMyReviews(
            @Valid @ValidPage @RequestParam Integer page,
            @RequestParam Long userId // 로그인 유저 주입받는 커스텀 어노테이션
    ) {
        int zeroBasedPage = page - 1;

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 사용자가 존재하지 않습니다."));

        Page<Review> reviewPage = reviewQueryService.getMyReviews(user, zeroBasedPage);
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewListDTO(reviewPage));
    }



}

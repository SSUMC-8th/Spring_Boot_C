package umc.study.web.controller;

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
import umc.study.apiPayload.ApiResponse;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.UserMissionConverter;
import umc.study.domain.mapping.UserMission;
import umc.study.service.UserMissionService.UserMissionCommandService;
import umc.study.service.UserMissionService.UserMissionQueryService;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.UserMissionDTO.MyOngoingMissionListDTO;
import umc.study.web.dto.requestDTO.UserMissionRequestDTO;
import umc.study.web.dto.responseDTO.UserMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-missions")
@Tag(name = "user-mission-rest-controller", description = "도전 중인 미션 API")
public class UserMissionRestController {

    private final UserMissionCommandService userMissionCommandService;
    private final UserMissionQueryService userMissionQueryService;

    @PostMapping("/challenge")
    @Operation(summary = "미션 도전")
    public ApiResponse<UserMissionResponseDTO.ChallengeResult> challenge(
            @RequestBody @Valid UserMissionRequestDTO.ChallengeRequest request) {
        return ApiResponse.onSuccess(userMissionCommandService.challenge(request));
    }

    @GetMapping("/me/ongoing")
    @Operation(summary = "내가 진행 중인 미션 목록", description = "userId를 기반으로 내가 진행 중인 미션 목록을 페이지 단위로 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "COMMON200",
                    description = "성공",
                    content = @Content(schema = @Schema(implementation = MyOngoingMissionListDTO.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "PAGE400",
                    description = "page 값이 1보다 작습니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자 ID"),
            @Parameter(name = "page", description = "1부터 시작하는 페이지 번호")
    })
    public ApiResponse<MyOngoingMissionListDTO> getMyOngoingMissions(
            @RequestParam Long userId,
            @Valid @ValidPage @RequestParam Integer page
    ) {
        int zeroBasedPage = page - 1;
        Page<UserMission> userMissionPage = userMissionQueryService.getMyOngoingMissions(userId, zeroBasedPage);
        return ApiResponse.onSuccess(UserMissionConverter.toMyOngoingListDTO(userMissionPage));
    }
}


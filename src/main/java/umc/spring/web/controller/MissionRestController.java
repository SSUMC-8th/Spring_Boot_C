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
import umc.spring.service.missionservice.MissionCommandService;
import umc.spring.web.dto.MissionRequest;
import umc.spring.web.dto.MissionResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @Operation(summary = "미션 도전하기", description = "특정 미션에 도전합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "미션 도전 성공",
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
                    description = "미션 또는 회원을 찾을 수 없음",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            )
    })
    @PostMapping("/accept")
    public umc.spring.apiPayload.ApiResponse<MissionResponse.AcceptMissionResultDTO> acceptMission(
            @Parameter(description = "미션 도전 정보", required = true)
            @Valid @RequestBody MissionRequest.AcceptMissionDTO request,
            @Parameter(description = "회원 ID", required = true)
            @RequestParam Long memberId) {
        return umc.spring.apiPayload.ApiResponse.onSuccess(missionCommandService.acceptMission(request, memberId));
    }
}
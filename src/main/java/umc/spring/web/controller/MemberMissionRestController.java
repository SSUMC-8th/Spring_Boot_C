package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.service.MemberMissionService.MemberMissionQueryService;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.web.dto.MissionChallengeRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/mission_update")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/")
    public ResponseEntity<String> challengeMission(@Valid @RequestBody MissionChallengeRequestDTO request) {
        Long memberId = request.getMemberId();
        Long missionId = request.getMissionId();

        MemberMission result = memberMissionCommandService.startMission(memberId, missionId);

        return ResponseEntity.ok("도전 시작 ID: " + result.getId());
    }

    private final MemberMissionQueryService memberMissionQueryService;

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "진행 중인 미션 목록 조회", description = "회원이 도전 중인 미션 목록을 페이징으로 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 page 파라미터", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID (path variable)"),
            @Parameter(name = "page", description = "조회할 페이지 번호 (1 이상)")
    })
    public ApiResponse<MissionResponseDTO.MemberMissionResponseDTO.PageDTO> getOngoingMissions(
            @PathVariable Long memberId,
            @ValidPage @RequestParam(name = "page") Integer page
    ) {
        var result = memberMissionQueryService.getMyMissions(memberId, page-1);
        return ApiResponse.onSuccess(MemberMissionConverter.toPageDTO(result));

    }

    @PatchMapping("/complete")
    @Operation(summary = "진행중인 미션 완료 처리", description = "사용자의 미션을 완료 상태로 변경합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공적으로 완료 처리됨"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "미션이 존재하지 않음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "진행중인 미션이 아님")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 ID"),
            @Parameter(name = "missionId", description = "미션 ID")
    })
    public ApiResponse<String> completeMission(
            @RequestParam Long memberId,
            @RequestParam Long missionId) {

        memberMissionCommandService.completeMission(memberId, missionId);
        return ApiResponse.onSuccess("미션이 완료 처리되었습니다.");
    }

}

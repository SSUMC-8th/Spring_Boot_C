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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.service.MissionService.MissionQueryService;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.MissionDTO.MissionPreviewListDTO;
import umc.study.web.dto.requestDTO.MissionRequestDTO;
import umc.study.web.dto.responseDTO.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Validated
@Tag(name = "mission-rest-controller", description = "미션 관련 API")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    private final MissionQueryService missionQueryService;

    @PostMapping
    @Operation(summary = "가게 미션 등록")
    public ApiResponse<MissionResponseDTO.CreateResult> createMission(
            @RequestBody @Valid MissionRequestDTO.CreateMission request) {
        return ApiResponse.onSuccess(missionCommandService.createMission(request));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "storeId에 해당하는 가게의 미션을 페이지 단위로 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "COMMON200",
                    description = "성공",
                    content = @Content(schema = @Schema(implementation = MissionPreviewListDTO.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "PAGE400",
                    description = "page 값이 1보다 작습니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "AUTH003",
                    description = "access 토큰을 주세요!",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게 ID"),
            @Parameter(name = "page", description = "1부터 시작하는 페이지 번호")
    })
    public ApiResponse<MissionPreviewListDTO> getStoreMissions(
            @PathVariable Long storeId,
            @Valid @ValidPage @RequestParam Integer page
    ) {
        int zeroBasedPage = page - 1;
        Page<Mission> missionPage = missionQueryService.getMissionsByStore(storeId, zeroBasedPage);
        return ApiResponse.onSuccess(MissionConverter.toPreviewListDTO(missionPage));
    }
}


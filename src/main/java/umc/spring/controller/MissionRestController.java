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
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.dto.web.MissionRequestDTO;
import umc.spring.dto.web.MissionResponseDTO;
import umc.spring.service.missionservice.MissionCommandService;
import umc.spring.service.missionservice.MissionQueryService;
import umc.spring.validation.annotation.OneIndexedPage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
@Validated
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;
    private final static int PAGE_SIZE = 10;

    @PostMapping
    @Operation(summary = "미션 생성", description = "특정 가게에 새로운 미션을 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "미션 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "가게를 찾을 수 없음")
    })
    public BaseResponse<MissionResponseDTO.CreateMissionResultDTO> createMission(
            @Parameter(description = "미션 생성 정보", required = true)
            @Valid @RequestBody MissionRequestDTO.CreateMissionDTO request) {

        return BaseResponse.onSuccess(missionCommandService.createMission(request));
    }

    @Operation(summary = "미션 도전하기", description = "특정 미션에 도전합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "미션 도전 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "미션 또는 회원을 찾을 수 없음")
    })
    @PostMapping("/accept")
    public BaseResponse<MissionResponseDTO.AcceptMissionResultDTO> acceptMission(
            @Parameter(description = "미션 도전 정보", required = true)
            @Valid @RequestBody MissionRequestDTO.AcceptMissionDTO request,
            @Parameter(description = "회원 ID", required = true)
            @RequestParam Long memberId) {
        return BaseResponse.onSuccess(missionCommandService.acceptMission(request, memberId));
    }

    @GetMapping
    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "미션 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "가게를 찾을 수 없음"),
            @ApiResponse(responseCode = "400", description = "잘못된 페이지 번호")
    })
    public BaseResponse<MissionResponseDTO.MissionListDTO> getMissions(
            @Parameter(description = "가게 ID", required = true)
            @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1부터 시작)", required = false)
            @RequestParam(defaultValue = "1") @OneIndexedPage Integer page) {

        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);
        Page<Mission> missionPage = missionQueryService.getMissionsByStore(storeId, pageRequest);

        return BaseResponse.onSuccess(MissionConverter.toMissionListDTO(missionPage));
    }


}
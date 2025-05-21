package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.ExistMemberMissionMission;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MemberMissionResponseDTO.memberMissionResultDTO> challengeMission(@ExistMemberMissionMission @PathVariable(name = "missionId") Long missionId){

        UserMission userMission = missionCommandService.challengeMission(missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toMemberMissionResultDTO(userMission));
    }

    @GetMapping("/progress")
    @Operation(summary = "진행 중인 미션 목록 조회 API", description = "현재 로그인한 사용자의 진행 중인 미션 목록을 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
        public ApiResponse<MissionResponseDTO.MissionUserPreViewListDTO> getMyMissionList( @ValidPage @RequestParam(name = "page") Integer page) {

        Page<UserMission> missionList = missionQueryService.findAllMissions(page);
        return ApiResponse.onSuccess(MemberMissionConverter.missionUserPreViewListDTO(missionList));
    }

}

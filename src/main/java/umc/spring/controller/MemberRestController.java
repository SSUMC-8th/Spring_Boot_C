package umc.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.BaseResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.dto.web.*;
import umc.spring.service.membermissionservice.MemberMissionCommandService;
import umc.spring.service.membermissionservice.MemberMissionQueryService;
import umc.spring.service.memberservice.MemberCommandService;
import umc.spring.service.memberservice.MemberQueryService;
import umc.spring.validation.annotation.OneIndexedPage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final MemberMissionQueryService memberMissionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;
    private final static int PAGE_SIZE = 10;

    @PostMapping("/join")
    public BaseResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request) {

        Member member = memberCommandService.joinMember(request);

        return BaseResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API",description = "유저가 로그인하는 API입니다.")
    public BaseResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody @Valid MemberRequestDTO.LoginRequestDTO request) {
        return BaseResponse.onSuccess(memberCommandService.loginMember(request));
    }

    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
            description = "유저가 내 정보를 조회하는 API입니다.",
            security = { @SecurityRequirement(name = "JWT TOKEN") }
    )
    public BaseResponse<MemberResponseDTO.MemberInfoDTO> getMyInfo(HttpServletRequest request) {
        return BaseResponse.onSuccess(memberQueryService.getMemberInfo(request));
    }

    @GetMapping("/{memberId}/missions/in-progress")
    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "특정 회원이 현재 진행중인 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "진행중인 미션 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음"),
            @ApiResponse(responseCode = "400", description = "잘못된 페이지 번호")
    })
    public BaseResponse<MemberMissionResponseDTO.MemberMissionListDTO> getInProgressMissions(
            @Parameter(description = "회원 ID", required = true)
            @PathVariable Long memberId,
            @Parameter(description = "페이지 번호 (1부터 시작)", required = false)
            @RequestParam(defaultValue = "1") @OneIndexedPage Integer page) {

        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);
        Page<MemberMission> memberMissionPage = memberMissionQueryService.getInProgressMissions(memberId, pageRequest);

        return BaseResponse.onSuccess(MemberMissionConverter.toMemberMissionListDTO(memberMissionPage));
    }

    @PutMapping("/{memberId}/missions/{memberMissionId}/complete")
    @Operation(summary = "미션 완료 처리", description = "진행중인 미션을 완료 상태로 변경합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "미션 완료 처리 성공"),
            @ApiResponse(responseCode = "404", description = "미션을 찾을 수 없음"),
            @ApiResponse(responseCode = "400", description = "진행중인 미션이 아니거나 기간이 만료됨")
    })
    public BaseResponse<MissionResponseDTO.CompleteMissionResultDTO> completeMission(
            @Parameter(description = "회원 ID", required = true)
            @PathVariable Long memberId,
            @Parameter(description = "회원 미션 ID", required = true)
            @PathVariable Long memberMissionId) {

        MissionRequestDTO.CompleteMissionDTO request = new MissionRequestDTO.CompleteMissionDTO(memberMissionId, memberId);

        return BaseResponse.onSuccess(memberMissionCommandService.completeMission(request));
    }

}

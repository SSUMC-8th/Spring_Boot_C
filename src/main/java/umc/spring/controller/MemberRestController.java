package umc.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.BaseResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.dto.web.MemberMissionResponseDTO;
import umc.spring.dto.web.MemberRequestDTO;
import umc.spring.dto.web.MemberResponseDTO;
import umc.spring.service.membermissionservice.MemberMissionQueryService;
import umc.spring.service.memberservice.MemberCommandService;
import umc.spring.validation.annotation.OneIndexedPage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberMissionQueryService memberMissionQueryService;
    private final static int PAGE_SIZE = 10;

    @PostMapping("/")
    public BaseResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request) {

        Member member = memberCommandService.joinMember(request);

        return BaseResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
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

}

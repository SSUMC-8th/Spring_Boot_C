package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.service.MemberService.MemberCommandService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;


    // @PostMapping("/")
    // public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
    //     Member member = memberCommandService.joinMember(request);
    //     return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    // }

    // @PostMapping("/signup")
    // public String joinMember(@ModelAttribute("memberJoinDto") MemberRequestDTO.JoinDto request, // 협업시에는 기존 RequestBody 어노테이션을 붙여주시면 됩니다!
    //                          BindingResult bindingResult,
    //                          Model model) {
    //     if (bindingResult.hasErrors()) {
    //         // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지합니다.
    //         return "signup";
    //     }

    //     try {
    //         memberCommandService.joinMember(request);
    //         return "redirect:/login";
    //     } catch (Exception e) {
    //         // 회원가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고, signup 페이디를 유지합니다.
    //         model.addAttribute("error", e.getMessage());
    //         return "signup";
    //     }
    // }

    // @GetMapping("/login")
    // public String loginPage() {
    //     return "login";
    // }

    // @GetMapping("/signup")
    // public String signupPage(Model model) {
    //     model.addAttribute("memberJoinDto", new MemberRequestDTO.JoinDto());
    //     return "signup";
    // }

    // @GetMapping("/home")
    // public String home(){
    //     return "home";
    // }

    // @GetMapping("/admin")
    // public String admin() {
    //     return "admin";
    // }

    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API",description = "유저가 로그인하는 API입니다.")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody @Valid MemberRequestDTO.LoginRequestDTO request) {
        return ApiResponse.onSuccess(memberCommandService.loginMember(request));
    }

    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
            description = "유저가 내 정보를 조회하는 API입니다.",
            security = { @SecurityRequirement(name = "JWT TOKEN") }
    )
    public ApiResponse<MemberResponseDTO.MemberInfoDTO> getMyInfo(HttpServletRequest request) {
        return ApiResponse.onSuccess(memberQueryService.getMemberInfo(request));
    }

    @PostMapping("/join")
    @Operation(summary = "회원가입 API", description = "회원가입을 진행합니다.")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> joinMemberRest(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
}

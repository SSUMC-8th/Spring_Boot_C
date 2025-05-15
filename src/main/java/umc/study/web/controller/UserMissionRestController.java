package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.UserMissionService.UserMissionCommandService;
import umc.study.web.dto.requestDTO.UserMissionRequestDTO;
import umc.study.web.dto.responseDTO.UserMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-missions")
@Tag(name = "user-mission-rest-controller", description = "도전 중인 미션 API")
public class UserMissionRestController {

    private final UserMissionCommandService userMissionCommandService;

    @PostMapping("/challenge")
    @Operation(summary = "미션 도전")
    public ApiResponse<UserMissionResponseDTO.ChallengeResult> challenge(
            @RequestBody @Valid UserMissionRequestDTO.ChallengeRequest request) {
        return ApiResponse.onSuccess(userMissionCommandService.challenge(request));
    }
}


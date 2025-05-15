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
import umc.study.service.MissionService.MissionCommandService;
import umc.study.web.dto.requestDTO.MissionRequestDTO;
import umc.study.web.dto.responseDTO.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Tag(name = "mission-rest-controller", description = "미션 관련 API")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping
    @Operation(summary = "가게 미션 등록")
    public ApiResponse<MissionResponseDTO.CreateResult> createMission(
            @RequestBody @Valid MissionRequestDTO.CreateMission request) {
        return ApiResponse.onSuccess(missionCommandService.createMission(request));
    }
}


package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ExistMemberMissionMission;
import umc.spring.web.dto.MemberMissionResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MemberMissionResponseDTO.memberMissionResultDTO> challengeMission(@ExistMemberMissionMission @PathVariable(name = "missionId") Long missionId){

        UserMission userMission = missionCommandService.challengeMission(missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toMemberMissionResultDTO(userMission));
    }

}

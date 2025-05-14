package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.web.dto.MissionChallengeRequestDTO;

@RestController
@RequiredArgsConstructor
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
}

package umc.spring.dto.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

/**
 * 미션 진행 정보 DTO
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MissionProgressDTO {

    private Long memberMissionId;

    private Long missionPoint;

    private MissionStatus missionStatus;

    private String storeName;

    private String missionContent;

    private Boolean missionIsReviewed;

    private LocalDateTime startedAt;

    private String cursorValue;
}

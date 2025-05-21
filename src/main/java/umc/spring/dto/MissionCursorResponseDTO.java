package umc.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.FoodCategory;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionCursorResponseDTO {
    private String memberAddress;
    private Long memberPoint;
    private Long completedMissions;
    private Long daysRemaining;
    private String storeName;
    private FoodCategory foodType;
    private String missionContent;
    private Long missionPoint;
    private String cursorValue;
}
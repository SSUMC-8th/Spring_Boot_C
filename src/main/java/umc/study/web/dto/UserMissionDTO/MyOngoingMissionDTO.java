package umc.study.web.dto.UserMissionDTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MyOngoingMissionDTO {
    private String storeName;
    private String title;
    private Integer point;
    private LocalDate deadline;
}

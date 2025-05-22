package umc.study.web.dto.MissionDTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MissionPreviewDTO {
    private String title;
    private Integer point;
    private LocalDate deadline;
    private String status;
}
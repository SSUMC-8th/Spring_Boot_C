package umc.study.web.dto.MissionDTO;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MissionPreviewListDTO {
    private List<MissionPreviewDTO> missionList;
    private boolean isFirst;
    private boolean isLast;
    private int totalPage;
    private long totalElements;
    private int listSize;
}
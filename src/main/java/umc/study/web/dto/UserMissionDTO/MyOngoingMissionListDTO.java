package umc.study.web.dto.UserMissionDTO;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MyOngoingMissionListDTO {
    private List<MyOngoingMissionDTO> missionList;
    private boolean isFirst;
    private boolean isLast;
    private int totalPage;
    private long totalElements;
    private int listSize;
}
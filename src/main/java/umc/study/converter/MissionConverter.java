package umc.study.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.web.dto.MissionDTO.MissionPreviewDTO;
import umc.study.web.dto.MissionDTO.MissionPreviewListDTO;
import umc.study.web.dto.requestDTO.MissionRequestDTO;
import umc.study.web.dto.responseDTO.MissionResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissionConverter {

    public Mission toEntity(MissionRequestDTO.CreateMission dto, Store store) {
        return Mission.builder()
                .store(store)
                .point(dto.getPoint())
                .deadline(dto.getDeadline())
                .missionSpec(dto.getMissionSpec())
                .build();
    }

    public MissionResponseDTO.CreateResult toResponse(Mission mission) {
        return new MissionResponseDTO.CreateResult(
                mission.getId(),
                mission.getStore().getName(),
                mission.getPoint(),
                mission.getDeadline(),
                mission.getMissionSpec()
        );
    }

    public static MissionPreviewDTO toPreviewDTO(Mission mission) {
        return MissionPreviewDTO.builder()
                .title(mission.getMissionSpec())  // title로 missionSpec을 노출
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .status(mission.getDeadline().isBefore(LocalDate.now()) ? "CLOSED" : "ONGOING")
                .build();
    }

    public static MissionPreviewListDTO toPreviewListDTO(Page<Mission> missionPage) {
        List<MissionPreviewDTO> list = missionPage.getContent().stream()
                .map(MissionConverter::toPreviewDTO)
                .collect(Collectors.toList());

        return MissionPreviewListDTO.builder()
                .missionList(list)
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(list.size())
                .build();
    }
}


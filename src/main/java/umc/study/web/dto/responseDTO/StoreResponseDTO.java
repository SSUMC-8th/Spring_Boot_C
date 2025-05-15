package umc.study.web.dto.responseDTO;

import lombok.*;

import java.time.LocalDateTime;

public class StoreResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class inputResultDTO {
        Long storeId;
        LocalDateTime createdAt;
    }
}

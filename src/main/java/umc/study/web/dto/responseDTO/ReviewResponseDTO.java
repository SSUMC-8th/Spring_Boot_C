package umc.study.web.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class ReviewResponseDTO {

    @Getter
    @AllArgsConstructor
    public static class CreateResult {
        private Long reviewId;
        private String storeName;
        private String userName;
        private String title;
        private Float score;
        private List<String> imageUrls;
    }
}

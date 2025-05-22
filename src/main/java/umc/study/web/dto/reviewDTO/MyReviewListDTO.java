package umc.study.web.dto.reviewDTO;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MyReviewListDTO {
    private List<MyReviewDTO> reviewList;
    private boolean isFirst;
    private boolean isLast;
    private int totalPage;
    private long totalElements;
    private int listSize;
}

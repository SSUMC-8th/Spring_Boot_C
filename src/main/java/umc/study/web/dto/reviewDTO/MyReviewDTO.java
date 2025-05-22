package umc.study.web.dto.reviewDTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class MyReviewDTO {
    private Float score;
    private String body;
    private LocalDate createdAt;
    private String storeName;
    private List<String> reviewImages;
    private OwnerReplyDTO ownerReply;  // nullable
}

package umc.study.web.dto.reviewDTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class OwnerReplyDTO {
    private String body;
    private LocalDate createdAt;
}

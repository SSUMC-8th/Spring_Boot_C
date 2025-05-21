package umc.study.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.User;

public interface ReviewQueryService {
    Page<Review> getMyReviews(User user, int page);
}

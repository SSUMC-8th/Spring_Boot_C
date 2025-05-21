package umc.study.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 필요한 경우 사용자 ID + Store ID로 중복 리뷰 여부 확인
    boolean existsByUserIdAndStoreId(Long userId, Long storeId);

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Page<Review> findAllByUser(User user, Pageable pageable);

}

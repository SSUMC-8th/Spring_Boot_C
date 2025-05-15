package umc.study.repository.ReviewRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    // 필요 시 리뷰 ID로 이미지 목록 조회도 가능
}
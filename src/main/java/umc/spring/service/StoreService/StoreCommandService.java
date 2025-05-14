package umc.spring.service.StoreService;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.ReviewRequestDTO;

public interface StoreCommandService {
    Review createReview(Long memberId, Long storeId, ReviewRequestDTO.ReveiwDTO request);
    Mission createMission(Long storeId,MissionRequestDTO.MissionDTO request);
}

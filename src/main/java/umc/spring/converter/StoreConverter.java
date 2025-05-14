package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static Review toReview(ReviewRequestDTO.ReveiwDTO request){
        return Review.builder()
                .title(request.getTitle())
                .rating(request.getRating())
                .content(request.getContent())
                .date(request.getDate())
                .build();
    }

    public static ReviewResponseDTO.reviewResultDTO toReveiwResultDTO(Review review) {
        return ReviewResponseDTO.reviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.MissionDTO request) {
        return Mission.builder()
                .content(request.getContent())
                .deadline(request.getDeadline())
                .point(request.getPoint())
                .build();
    }

    public static MissionResponseDTO.missionResultDTO toMissionResultDTO(Mission mission) {
        return MissionResponseDTO.missionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}

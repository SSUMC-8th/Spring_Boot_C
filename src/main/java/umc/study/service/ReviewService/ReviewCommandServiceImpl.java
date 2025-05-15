package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.domain.ReviewImage;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.repository.ReviewRepository.ReviewImageRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.requestDTO.ReviewRequestDTO;
import umc.study.web.dto.responseDTO.ReviewResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ReviewConverter reviewConverter;

    @Override
    public ReviewResponseDTO.CreateResult createReview(ReviewRequestDTO.CreateReview dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Review review = reviewConverter.toReviewEntity(dto, store, user);
        reviewRepository.save(review);

        List<ReviewImage> images = reviewConverter.toReviewImages(dto.getImageUrls(), review);
        reviewImageRepository.saveAll(images);

        return reviewConverter.toResponse(review, images);
    }
}


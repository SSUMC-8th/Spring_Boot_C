package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.*;
import umc.spring.dto.web.StoreRequestDTO;
import umc.spring.dto.web.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Store toStoreDTO(StoreRequestDTO.CreateStoreDTO request, Food food, Location location) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .rating(request.getRating())
                .food(food)
                .location(location)
                .build();
    }

    public static StoreOpeningHours toStoreOpeningHoursDTO(StoreRequestDTO.OpeningHoursDTO openingHours, Store store) {
        return StoreOpeningHours.builder()
                .store(store)
                .dayOfWeek(openingHours.getDayOfWeek())
                .startingTime(openingHours.getStartingTime())
                .endingTime(openingHours.getEndingTime())
                .build();
    }

    public static StoreResponseDTO.CreateStoreResultDTO toCreateStoreResultDTO(Long storeId) {
        return StoreResponseDTO.CreateStoreResultDTO.builder()
                .storeId(storeId)
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
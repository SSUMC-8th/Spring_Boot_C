package umc.spring.converter;

import umc.spring.domain.Food;
import umc.spring.domain.Location;
import umc.spring.domain.Store;
import umc.spring.domain.StoreOpeningHours;
import umc.spring.web.dto.StoreRequest;
import umc.spring.web.dto.StoreResponse;

public class StoreConverter {

    public static Store toStore(StoreRequest.CreateStoreDTO request, Food food, Location location) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .rating(request.getRating())
                .food(food)
                .location(location)
                .build();
    }

    public static StoreOpeningHours toStoreOpeningHours(StoreRequest.OpeningHoursDTO openingHours, Store store) {
        return StoreOpeningHours.builder()
                .store(store)
                .dayOfWeek(openingHours.getDayOfWeek())
                .startingTime(openingHours.getStartingTime())
                .endingTime(openingHours.getEndingTime())
                .build();
    }

    public static StoreResponse.CreateStoreResultDTO toCreateStoreResultDTO(Long storeId) {
        return StoreResponse.CreateStoreResultDTO.builder()
                .storeId(storeId)
                .build();
    }
}
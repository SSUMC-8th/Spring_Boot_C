package umc.study.converter;

import umc.study.domain.FoodCategory;
import umc.study.domain.mapping.PreferenceFood;

import java.util.List;
import java.util.stream.Collectors;

public class UserPreferConverter {

    public static List<PreferenceFood> toUserPreferList(List<FoodCategory> foodCategoryList){

        return foodCategoryList.stream()
                .map(foodCategory ->
                        PreferenceFood.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}

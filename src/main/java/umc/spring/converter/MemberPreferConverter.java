package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.enums.FoodCategory;
import umc.spring.domain.mapping.MemberPrefer;

import java.util.List;

public class MemberPreferConverter {

    // FoodCategory 리스트를 기반으로 MemberPrefer 객체들을 생성하여 Member에 연결
    public static void addMemberPreferences(List<FoodCategory> foodCategoryList, Member member) {
        foodCategoryList.forEach(category ->
                MemberPrefer.createMemberPrefer(member, category)
        );
    }
}

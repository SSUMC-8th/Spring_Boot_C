package umc.spring.converter;

import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodHandler;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.domain.Member;
import umc.spring.domain.enums.FoodCategory;
import umc.spring.domain.enums.Gender;
import umc.spring.dto.web.MemberRequestDTO;
import umc.spring.dto.web.MemberResponseDTO;

import java.util.List;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMemberDTO(MemberRequestDTO.JoinDTO request) {

        Gender gender = convertGender(request.getGender());

        List<FoodCategory> foodCategories = convertFoodCategories(request.getPreferCategory());

        // Member 생성
        Member member = Member.builder()
                .name(request.getName())
                .gender(gender)
                .birthDate(request.getBirthDate())
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .build();

        // 선호도 추가
        if (foodCategories != null && !foodCategories.isEmpty()) {
            member.addFoodPreferences(foodCategories);
        }

        return member;
    }

    // Gender 변환 로직 분리
    private static Gender convertGender(Integer genderCode) {
        if (genderCode == null) {
            throw new MemberHandler(ErrorStatus.INVALID_GENDER);
        }

        return switch (genderCode) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            case 3 -> Gender.NONE;
            default -> throw new MemberHandler(ErrorStatus.INVALID_GENDER);
        };
    }

    // FoodCategory 변환 로직 분리
    private static List<FoodCategory> convertFoodCategories(List<String> categoryNames) {
        if (categoryNames == null || categoryNames.isEmpty()) {
            return null;
        }

        return categoryNames.stream()
                .map(categoryName -> {
                    try {
                        return FoodCategory.valueOf(categoryName.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new FoodHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND);
                    }
                })
                .toList();
    }
}

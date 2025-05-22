package umc.spring.service.memberservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.Member;
import umc.spring.domain.enums.FoodCategory;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    @Override
    public Member joinMember(MemberRequestDTO.JoinDTO request) {

        Member newMember = MemberConverter.toMember(request);

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(categoryName -> {
                    try {
                        return FoodCategory.valueOf(categoryName);  // 문자열 -> Enum 변환
                    } catch (IllegalArgumentException e) {
                        throw new GeneralException(ErrorStatus.FOOD_CATEGORY_NOT_FOUND);
                    }
                })
                .collect(Collectors.toList());

        // Member에 선호 카테고리 추가
        MemberPreferConverter.addMemberPreferences(foodCategoryList, newMember);

        return memberRepository.save(newMember);
    }
}

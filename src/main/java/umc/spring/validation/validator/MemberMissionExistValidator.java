package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.ExistMemberMissionMission;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberMissionExistValidator implements ConstraintValidator<ExistMemberMissionMission, Long> {

    private final MissionQueryService missionQueryService;
    private final MemberQueryService memberQueryService;

    @Override
    public void initialize(ExistMemberMissionMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        Long currentUserId = memberQueryService.findAnyMember()
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."))
                .getId();

        Optional<UserMission> target = missionQueryService.findUserMissionByUserIdAndMissionId(currentUserId, value);

        if (target.isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("이미 도전 중인 미션입니다.").addConstraintViolation();
            return false;
        }
        return true;
    }
}

package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.web.dto.MissionChallengeRequestDTO;

@Component
@RequiredArgsConstructor
public class NotAlreadyChallengingValidator implements ConstraintValidator<umc.spring.validation.annotation.NotAlreadyChallenging, MissionChallengeRequestDTO> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(MissionChallengeRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getMemberId() == null || dto.getMissionId() == null) {
            return true; // null 여부는 @NotNull으로 검증됨
        }

        // ✅ ENUM 타입으로 직접 전달
        boolean exists = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                dto.getMemberId(),
                dto.getMissionId(),
                MissionStatus.IN_PROGRESS
        );

        return !exists; // 존재하면 false (이미 도전 중이면 검증 실패)
    }
}

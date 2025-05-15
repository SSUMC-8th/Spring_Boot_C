package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.web.dto.requestDTO.UserMissionRequestDTO;

@Component
@RequiredArgsConstructor
public class UserMissionUniqueCheckValidator implements ConstraintValidator<
        umc.study.validation.annotation.UserMissionUniqueCheck,
        UserMissionRequestDTO.ChallengeRequest> {

    private final UserMissionRepository userMissionRepository;

    @Override
    public boolean isValid(UserMissionRequestDTO.ChallengeRequest dto, ConstraintValidatorContext context) {
        if (dto.getUserId() == null || dto.getMissionId() == null) return true;
        return !userMissionRepository.existsByUserIdAndMissionId(dto.getUserId(), dto.getMissionId());
    }
}

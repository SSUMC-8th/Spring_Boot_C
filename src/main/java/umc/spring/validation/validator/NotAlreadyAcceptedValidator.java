package umc.spring.validation.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.missionservice.MissionValidationService;
import umc.spring.validation.annotation.NotAlreadyAccepted;
import umc.spring.web.dto.MissionRequestDTO;

@Component
@RequiredArgsConstructor
public class NotAlreadyAcceptedValidator implements ConstraintValidator<NotAlreadyAccepted, MissionRequestDTO.AcceptMissionDTO> {

    private final MissionValidationService missionValidationService;

    @Override
    public void initialize(NotAlreadyAccepted constraintAnnotation) {
        // 초기화 로직
    }

    @Override
    public boolean isValid(MissionRequestDTO.AcceptMissionDTO request, ConstraintValidatorContext context) {
        if (request == null || request.getMissionId() == null) {
            return true; // 다른 검증에서 처리
        }

        try {
            // HttpServletRequest에서 memberId 추출
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest httpRequest = attributes.getRequest();
            String memberIdParam = httpRequest.getParameter("memberId");

            if (memberIdParam == null) {
                return true; // memberId가 없으면 다른 검증에서 처리
            }

            Long memberId = Long.parseLong(memberIdParam);
            Long missionId = request.getMissionId();

            // 서비스를 통해 검증 수행
            boolean alreadyAccepted = missionValidationService.isAlreadyAccepted(memberId, missionId);

            if (alreadyAccepted) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_ACCEPTED.name())
                        .addConstraintViolation();
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            // memberId가 숫자가 아닌 경우 다른 검증에서 처리
            return true;
        } catch (Exception e) {
            // 기타 예외 발생 시 유효하다고 간주
            return true;
        }
    }
}

package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.OneIndexedPage;

@Component
public class OneIndexedPageValidator implements ConstraintValidator<OneIndexedPage, Integer> {

    private String message;

    @Override
    public void initialize(OneIndexedPage constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        // null 값은 유효하다고 간주 (기본값 사용)
        if (page == null) {
            return true;
        }

        // 페이지 번호 유효성 검사 (1 이상이어야 함)
        if (page <= 0) {
            // 기본 메시지 비활성화
            context.disableDefaultConstraintViolation();

            // 커스텀 메시지 설정
            context.buildConstraintViolationWithTemplate(ErrorStatus.INVALID_PAGE_NUMBER.name())
                    .addConstraintViolation();

            // 유효하지 않음을 반환
            return false;
        }

        // 유효한 페이지 번호
        return true;
    }
}
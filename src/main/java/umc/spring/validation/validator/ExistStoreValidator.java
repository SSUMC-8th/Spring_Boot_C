package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.storeservice.StoreValidationService;
import umc.spring.validation.annotation.ExistStore;

@Component
@RequiredArgsConstructor
public class ExistStoreValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreValidationService storeValidationService;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        // 초기화 로직
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        // null 값은 다른 검증(@NotNull)에서 처리
        if (storeId == null) {
            return true;
        }

        // 서비스를 통해 가게 존재 여부 확인
        boolean exists = storeValidationService.existsStore(storeId);

        if (!exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.name())
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
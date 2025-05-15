package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.validation.annotation.ValidStoreId;

@Component
public class StoreIdValidator implements ConstraintValidator<ValidStoreId, Long> {
    private final StoreRepository storeRepository;

    public StoreIdValidator(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        return storeId != null && storeRepository.existsById(storeId);
    }
}

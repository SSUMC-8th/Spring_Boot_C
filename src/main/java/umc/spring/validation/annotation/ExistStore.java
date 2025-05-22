package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.ExistStoreValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistStoreValidator.class)
@Target({ElementType.PARAMETER})  // @PathVariable, @RequestParam 같은 파라미터에 붙일 수 있게
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStore {
    String message() default "존재하지 않는 가게입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

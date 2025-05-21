package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.ExistStoreValidator;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistStoreValidator.class)
@Documented
public @interface ExistStore {

    String message() default "존재하지 않는 가게입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

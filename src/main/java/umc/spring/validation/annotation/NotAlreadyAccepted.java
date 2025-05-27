package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.CategoriesExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoriesExistValidator.class)
@Target( { ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotAlreadyAccepted {

    String message() default "이미 도전 중인 미션입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

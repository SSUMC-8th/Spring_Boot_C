package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.NotAlreadyChallengingValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotAlreadyChallengingValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotAlreadyChallenging {

    String message() default "이미 해당 미션에 도전 중입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import umc.spring.validation.validator.PageValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {
}

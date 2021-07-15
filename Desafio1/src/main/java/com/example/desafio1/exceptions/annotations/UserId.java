package com.example.desafio1.exceptions.annotations;

import com.example.desafio1.exceptions.validators.UserIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.*;

@Documented
@PositiveOrZero(message = "Id should be a natural number")
@Constraint(validatedBy = {UserIdValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface UserId {
    String message() default "Id is not present";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}

package com.example.desafio1.exceptions.annotations;

import com.example.desafio1.exceptions.validators.UserFollowValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UserFollowValidator.class)
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UserFollows{
    String message() default "";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}

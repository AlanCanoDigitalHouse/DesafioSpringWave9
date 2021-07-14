package com.socialmeli.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = DateValidationImp.class)
@Target( { FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateValidation {
    String message() default "bad date";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String pattern();
}
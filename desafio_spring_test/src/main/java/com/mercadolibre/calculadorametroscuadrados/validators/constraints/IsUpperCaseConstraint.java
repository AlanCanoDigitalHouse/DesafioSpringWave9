package com.mercadolibre.calculadorametroscuadrados.validators.constraints;

import com.mercadolibre.calculadorametroscuadrados.validators.validators.IsUpperCase;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsUpperCase.class)
public @interface IsUpperCaseConstraint {
    String message() default "Debe empezar por máyuscula";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

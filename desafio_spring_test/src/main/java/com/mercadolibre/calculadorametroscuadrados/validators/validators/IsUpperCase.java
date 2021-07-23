package com.mercadolibre.calculadorametroscuadrados.validators.validators;

import com.mercadolibre.calculadorametroscuadrados.validators.constraints.IsUpperCaseConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsUpperCase implements ConstraintValidator<IsUpperCaseConstraint, String> {
    @Override
    public void initialize(IsUpperCaseConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        return Character.isUpperCase(str.charAt(0));
    }
}

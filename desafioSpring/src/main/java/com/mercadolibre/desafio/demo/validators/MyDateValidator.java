package com.mercadolibre.desafio.demo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

class MyDateValidator implements ConstraintValidator<ValidDate, Date> {
    public void initialize(ValidDate constraint) {
    }

    public boolean isValid(Date value, ConstraintValidatorContext context) {
        // validate the value here.
        Date date = new Date();
        return date.after(value);
    }
}
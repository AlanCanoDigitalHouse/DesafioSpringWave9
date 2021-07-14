package com.socialmeli.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateValidationImp implements ConstraintValidator<DateValidation, Object> {

    private String pattern;

    @Override
    public void initialize(DateValidation dateValidation) {
        this.pattern = dateValidation.pattern();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(value instanceof String) {
            String date = (String) value;
            if(!isValid(date)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValid(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(this.pattern));
        } catch (DateTimeException e) {
            return false;
        }

        return true;
    }

}
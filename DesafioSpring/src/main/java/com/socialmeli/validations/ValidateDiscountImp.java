package com.socialmeli.validations;

import com.socialmeli.dtos.requests.PromoPostRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidateDiscountImp implements ConstraintValidator<ValidateDiscount, PromoPostRequestDTO> {

    @Override
    public void initialize(ValidateDiscount dateValidation) {

    }

    @Override
    public boolean isValid(PromoPostRequestDTO value, ConstraintValidatorContext context) {

        return !(value.getHasPromo() != null && value.getHasPromo() && value.getDiscount() == null);

    }

}

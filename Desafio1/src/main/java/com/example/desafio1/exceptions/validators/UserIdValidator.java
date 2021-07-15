package com.example.desafio1.exceptions.validators;

import com.example.desafio1.dto.User;
import com.example.desafio1.exceptions.UserNotFoundException;
import com.example.desafio1.exceptions.annotations.UserId;
import com.example.desafio1.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UserIdValidator implements ConstraintValidator<UserId,Integer> {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void initialize(UserId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        User user = userRepository.findUserById(value);
        if(Objects.isNull(user)) throw new UserNotFoundException(value);
        return true;
    }
}

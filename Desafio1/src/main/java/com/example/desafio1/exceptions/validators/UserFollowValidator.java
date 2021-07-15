package com.example.desafio1.exceptions.validators;

import com.example.desafio1.exceptions.UserAlreadyFollowingException;
import com.example.desafio1.exceptions.UserNotFoundException;
import com.example.desafio1.exceptions.annotations.UserFollows;
import com.example.desafio1.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.Objects;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class UserFollowValidator  implements ConstraintValidator<UserFollows, Object[]> {
    @Autowired
    private IUserRepository repository;

    @Override
    public void initialize(UserFollows constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        if (Objects.isNull(value[0]) || Objects.isNull(value[1])) {
            throw new NullPointerException("Value can't be null");
        }

        Integer v1 = (Integer)value[0];
        Integer v2 = (Integer) value[1];
        if(Objects.isNull(repository.findUserById(v1))) throw new UserNotFoundException(v1);
        if(Objects.isNull(repository.findUserById(v2))) throw new UserNotFoundException(v2);


        if(repository.isFollowing(v1,v2)) {
            throw new UserAlreadyFollowingException(v1, v2);
        }

        return true;
    }
}

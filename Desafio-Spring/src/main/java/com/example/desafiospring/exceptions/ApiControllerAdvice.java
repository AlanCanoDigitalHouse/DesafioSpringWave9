package com.example.desafiospring.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserNotExistException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR_USER);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(SameUserException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR_USER);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(NotHavePromoProducts ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR_USER);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(MethodArgumentTypeMismatchException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Accept only numbers for userId");
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(ConstraintViolationException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserDontHaveFollowersorFollowed ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR_LIST + "" + ex.TYPE);
    }



    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDto handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    public ErrorMessageDto processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError: fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(),"Validations Errors",fields);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserDontHavePostsException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR_POSTS_LIST);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(AlreadyFollow ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR_FOLLOW);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(DateTimeParseException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Correct format for date: 'dd/MM/yyyy'");
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(JsonParseException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Use correct format for post ");
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(NeedDiscountException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR_DISCOUNT);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(HasPromoException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR_PROMO);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(DontNeedDiscountException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR_PROMO_DISC);
    }

}
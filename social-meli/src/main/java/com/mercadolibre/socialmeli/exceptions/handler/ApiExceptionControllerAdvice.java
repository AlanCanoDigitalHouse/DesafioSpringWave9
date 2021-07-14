package com.mercadolibre.socialmeli.exceptions.handler;

import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerAlreadyFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(BindingResult result) {
        HashMap<String, String> errors = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "ValidationException", null, errors);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(HttpMessageNotReadableException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getClass().getSimpleName(), "the json object is not well formed", null);
    }

    @ExceptionHandler(value = {
            BuyerNotFoundException.class,
            SellerNotFoundException.class,
            SellerNotFollowedException.class,
            SellerAlreadyFollowedException.class
    })
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(Exception ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getClass().getSimpleName(), ex.getMessage(), null);
    }

}

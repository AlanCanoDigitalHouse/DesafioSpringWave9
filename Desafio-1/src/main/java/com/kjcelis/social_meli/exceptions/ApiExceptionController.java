package com.kjcelis.social_meli.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionController {
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleGeneralException(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage(exception.getMessage());
    }


}
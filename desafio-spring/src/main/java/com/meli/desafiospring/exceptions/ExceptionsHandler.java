package com.meli.desafiospring.exceptions;

import com.meli.desafiospring.exceptions.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionsHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException2(ConstraintViolationException exception) {
        Map<String,String> errMap = new HashMap<String, String>();
        errMap.put(exception.getClass().getSimpleName(), exception.getMessage());
        List<String> constraints = exception
                .getConstraintViolations()
                .stream()
                .map(Object::toString).collect(Collectors.toList());
        Integer counter = 0;
        for (String s : constraints) {
            errMap.put("Constraint " +counter+ ":", s);
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getLocalizedMessage(), errMap);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage badDateException(BadDateFormat exception){
        HashMap<String,String> err = new HashMap<>();
        err.put(exception.getClass().getSimpleName(), exception.getMessage());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getDateMessage(), err);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage userDoesNotExistException(UserDoesNotExist exception){
        Map<String,String> errMap = new HashMap<String, String>();
        errMap.put(exception.getClass().getSimpleName(), exception.getMessage());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), errMap);
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage postDetailIsNullException(PostDetailIsNullException exception){
        Map<String,String> errMap = new HashMap<String, String>();
        errMap.put(exception.getClass().getSimpleName(), exception.getMessage());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), errMap);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public ErrorMessage noPostsAvailableException(NoPostsAvailableException exception){
        Map<String,String> errMap = new HashMap<String, String>();
        errMap.put(exception.getClass().getSimpleName(), exception.getMessage());
        return new ErrorMessage(HttpStatus.OK.value(), exception.getMessage(), errMap);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage followUnfollowException(FollowUnfollowException exception){
        Map<String,String> errMap = new HashMap<String, String>();
        errMap.put(exception.getClass().getSimpleName(), exception.getMessage());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), errMap);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage finalExceptionHandler(Exception exception){
        Map<String,String> errMap = new HashMap<String, String>();
        errMap.put(exception.getClass().getSimpleName(), exception.getMessage());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), errMap);
    }

    public ErrorMessage processField( List<FieldError> fieldErrors){
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations errors", fields);
    }
}

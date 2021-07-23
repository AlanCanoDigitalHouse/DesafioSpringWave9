package com.squareMeter.exception.advice;

import com.squareMeter.exception.model.ErrorAttributes;
import com.squareMeter.exception.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorAttributes> argumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorAttributes errorAttributes = new ErrorAttributes("Error with a few attributes");
        fieldErrors.forEach(fieldError -> errorAttributes.addFieldError(fieldError.getField(), fieldError.getDefaultMessage()));
        return new ResponseEntity<>(errorAttributes, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> badBodyException(HttpMessageNotReadableException ex){
        return new ResponseEntity<>(new ErrorMessage("Error in the body","Probably the body have a unexpected symbol"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> defaultError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessage("Error", "Error generated"), HttpStatus.BAD_REQUEST);
    }
}

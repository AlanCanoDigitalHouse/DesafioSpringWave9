package com.example.tucasitatasacciones.exception.advice;

import com.example.tucasitatasacciones.exception.exception.DistrictNotExistsException;
import com.example.tucasitatasacciones.exception.model.ErrorAttributes;
import com.example.tucasitatasacciones.exception.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class DefaultControllerAdvice {
    @ExceptionHandler(DistrictNotExistsException.class)
    public ResponseEntity<ErrorMessage> districtNotExists(DistrictNotExistsException e) {
        return new ResponseEntity<>(new ErrorMessage("El Distrito no existe", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorAttributes> argumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorAttributes errorAttributes = new ErrorAttributes("Error referente al ingreso de atributos");
        fieldErrors.forEach(fieldError -> errorAttributes.addFieldError(fieldError.getField(), fieldError.getDefaultMessage()));
        return new ResponseEntity<>(errorAttributes, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> badBodyException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(new ErrorMessage("Error al ingresar el body", "Error al ingresar el body"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> defaultError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessage("Error", "Error "), HttpStatus.BAD_REQUEST);
    }
}

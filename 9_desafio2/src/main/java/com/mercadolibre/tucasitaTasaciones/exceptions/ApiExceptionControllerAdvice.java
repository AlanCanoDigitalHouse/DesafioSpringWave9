package com.mercadolibre.tucasitaTasaciones.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

    /**
     * Excepción para cuando no se encuentra el barrio en la base de datos
     * @return ErrorMessageException
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageException handlerException(ExceptionLocationNotFound exception) {
        return new ErrorMessageException(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    /**
     * Excepción para cuando el precio pasado no es igual al de la base de datos
     * para ese barrio pasado
     * @param exception
     * @return ErrorMessageException
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageException handlerException(ExceptionLocationPriceIncorrect exception) {
        return new ErrorMessageException(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    /**
     * Excepción para cuando un atributo del JSON no es del tipo correcto
     * @param exception
     * @return ErrorMessageException
     */
    @ExceptionHandler
    public ResponseEntity<ErrorMessageException> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception) {
        ErrorMessageException errorMessage = new ErrorMessageException(HttpStatus.BAD_REQUEST.value(),
                "Fallo al intentar convertir valor.");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    /**
     * Excepción para las validaciones
     * @param exception
     * @return ErrorMessage
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    private ErrorMessage processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validation error", fields);
    }

}

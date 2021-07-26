package com.mercadolibre.TuCasitaTasacionesDesafioTesting.handlers;

import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.ErrorMessageDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions.EmptyListException;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions.InvalidDistrictException;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions.NullListException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;


@ControllerAdvice(annotations = RestController.class)

public class TuCasitaTasacionesHandler {

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
        for (FieldError fieldError : fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }

    @ExceptionHandler(NullListException.class)
    public ResponseEntity<?> nullListException(NullListException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<?> emptyListException(EmptyListException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDistrictException.class)
    public ResponseEntity<?> invalidDistrictException(InvalidDistrictException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

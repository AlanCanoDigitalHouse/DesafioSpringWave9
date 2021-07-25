package com.mercadolibre.calculadorametroscuadrados.exceptions.environmentValidationException.advices;


import com.mercadolibre.calculadorametroscuadrados.exceptions.environmentValidationException.exceptions.NotFoundEnvironmentException;
import com.mercadolibre.calculadorametroscuadrados.exceptions.environmentValidationException.models.NotFoundMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class EnvironmentControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NotFoundEnvironmentException.class)
    public ResponseEntity<NotFoundMessage> handlerNotFoundEnvironmentException(
            NotFoundEnvironmentException e
    ) {
        return new ResponseEntity<>(new NotFoundMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        ),HttpStatus.BAD_REQUEST) ;
    }
}

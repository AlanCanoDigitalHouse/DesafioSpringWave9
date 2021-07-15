package com.mercadolibre.desafio.demo.exceptions.userException.advices;


import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.NoOrderException;
import com.mercadolibre.desafio.demo.exceptions.userException.models.BadRequestMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice(annotations = RestController.class)
public class JsonFormatControlllerAdvice {

    /**
     * TODO: Validates a HttpMessageNotReadableException /  JSON parse error
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<BadRequestMessage> handlerUserNotFoundException(
            HttpMessageNotReadableException e
    ) {
        return new ResponseEntity<>(new BadRequestMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        ),HttpStatus.BAD_REQUEST) ;
    }
}


package com.mercadolibre.desafio.demo.exceptions.userException.advices;

import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.NoOrderException;
import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.NotFoundUserException;
import com.mercadolibre.desafio.demo.exceptions.userException.models.BadRequestMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class OrderExceptionControllerAdvice {

    /**
     *  TODO: Validates NoOrderException / only expect a valid string to order ej:['name_asc','date_asc',...]
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoOrderException.class)
    public ResponseEntity<BadRequestMessage> handlerUserNotFoundException(
            NoOrderException e
    ) {
        return new ResponseEntity<>(new BadRequestMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        ),HttpStatus.BAD_REQUEST) ;
    }
}

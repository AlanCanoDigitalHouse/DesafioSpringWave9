package com.mercadolibre.calculadorametroscuadrados.exception.advices;

import com.mercadolibre.calculadorametroscuadrados.exception.DistrictDoesntExistException;
import com.mercadolibre.calculadorametroscuadrados.exception.models.BadRequestMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class DistrictExceptionControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DistrictDoesntExistException.class)
    public ResponseEntity<BadRequestMessage> handlerDistrictNotFoundException(DistrictDoesntExistException e) {
        return new ResponseEntity<>(new BadRequestMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getCustomMessage()
        ),HttpStatus.BAD_REQUEST) ;
    }

}

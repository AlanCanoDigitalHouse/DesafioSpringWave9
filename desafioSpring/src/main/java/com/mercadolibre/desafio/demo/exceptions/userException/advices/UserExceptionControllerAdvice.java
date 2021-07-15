package com.mercadolibre.desafio.demo.exceptions.userException.advices;

import com.mercadolibre.desafio.demo.exceptions.userException.models.BadRequestMessage;
import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.NameException;
import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.NotFoundUserException;
import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.RepeatUserIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice(annotations = RestController.class)
public class UserExceptionControllerAdvice {

    /**
     * TODO: Validates NotFoundUserException / Not found user in database
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NotFoundUserException.class)
    public ResponseEntity<BadRequestMessage> handlerUserNotFoundException(
            NotFoundUserException e
    ) {
        return new ResponseEntity<>(new BadRequestMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMESSAGE()
        ),HttpStatus.BAD_REQUEST) ;
    }

    /**
     * TODO: Validates RepeatUserIdException / User follow same User, ej: user1 follow -> user1
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RepeatUserIdException.class)
    public ResponseEntity<BadRequestMessage> handlerRepeatUserIdException(
            RepeatUserIdException e
    ) {
        return new ResponseEntity<>(new BadRequestMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getCustomMessage()
        ),HttpStatus.BAD_REQUEST) ;
    }


    /**
     * TODO: Validates MethodArgumentTypeMismatchException / values can not convert to required type
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BadRequestMessage> handlerNameException(
            MethodArgumentTypeMismatchException e
    ) {
        return new ResponseEntity<>(new BadRequestMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Failed to convert '" +e.getValue()+ "' to required type " + e.getRequiredType()
        ),HttpStatus.BAD_REQUEST) ;
    }




}

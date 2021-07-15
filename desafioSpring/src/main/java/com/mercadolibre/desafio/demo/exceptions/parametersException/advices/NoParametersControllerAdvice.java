package com.mercadolibre.desafio.demo.exceptions.parametersException.advices;

import com.mercadolibre.desafio.demo.exceptions.parametersException.models.NoParametersMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice(annotations = RestController.class)
public class NoParametersControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<NoParametersMessage> handlerValidationParamsException(
            MissingServletRequestParameterException e
    ) {
        return new ResponseEntity<NoParametersMessage>(new NoParametersMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                e.getParameterName()
        ),HttpStatus.BAD_REQUEST) ;
     }




//    @ResponseBody    MalformedURLException | UnknownHostException
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = HttpMessageNotReadableException.class)
//    public ResponseEntity<NoParametersMessage> handlerValidationBodyException(
//            HttpMessageNotReadableException e
//    ) {
//        System.out.println(e);
//        return new ResponseEntity<NoParametersMessage>(new NoParametersMessage(
//                HttpStatus.BAD_REQUEST.value(),
//                e.getMessage(),
//                "Required request body is missing"
//        ),HttpStatus.BAD_REQUEST) ;
//     }
}

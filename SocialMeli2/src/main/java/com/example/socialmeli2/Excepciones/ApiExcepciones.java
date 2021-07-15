package com.example.socialmeli2.Excepciones;

import com.example.socialmeli2.Excepciones.Type.IdNoEncontrado;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ApiExcepciones {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleValidationExceptions(IdNoEncontrado excepcion) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),excepcion.ERROR_MESSAGE);
    }


}

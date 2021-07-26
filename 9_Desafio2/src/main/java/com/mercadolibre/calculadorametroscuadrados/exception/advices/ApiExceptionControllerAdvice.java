package com.mercadolibre.calculadorametroscuadrados.exception.advices;

import com.mercadolibre.calculadorametroscuadrados.exception.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class) // escuchara un RestController, estara atento
public class ApiExceptionControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class) // indicamos que manejara una excepcion
    @ResponseBody // indicamos que se respondera por el body
    @ResponseStatus(value= HttpStatus.BAD_REQUEST) // definimos el estado en este caso
    public ErrorMessage handlerException(MethodArgumentNotValidException exception){

        BindingResult result = exception.getBindingResult(); // es un objeto de spring para validar errores
        List<FieldError> fieldErrors = result.getFieldErrors(); // errores de campos en los dtos y getFieldErrors devuelve todos los errores de campos
        return processField(fieldErrors);
    }

    public ErrorMessage processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>(); // para guardar los fields
        for (FieldError fieldError: fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields); // Error messege
    }

}

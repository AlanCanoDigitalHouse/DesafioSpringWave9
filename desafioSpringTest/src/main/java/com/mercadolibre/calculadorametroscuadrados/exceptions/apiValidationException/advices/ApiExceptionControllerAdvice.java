package com.mercadolibre.desafio.demo.exceptions.apiValidationException.advices;

import com.mercadolibre.desafio.demo.exceptions.apiValidationException.models.ErrorMessege;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class) // escuchara un RestController, estara atento
public class ApiExceptionControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class) // indicamos que manejara una excepcion
    @ResponseBody // indicamos que se respondera por el body
    @ResponseStatus(value= HttpStatus.BAD_REQUEST) // definimos el estado en este caso
    public ErrorMessege handlerException(MethodArgumentNotValidException exception){

        BindingResult resutl = exception.getBindingResult(); // es un objeto de spring para validar errores
        List<FieldError> fieldErrors = resutl.getFieldErrors(); // errores de campos en los dtos y getFieldErrors devuelve todos los errores de campos
        return processField(fieldErrors);
    }

    public ErrorMessege processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>(); // para guardar los fields
        for (FieldError fieldError: fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessege(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields); // Error messege
    }

    /**
     * Only date response validation for date parameter
     * Se definio esta seccion para que trabaje conjuntamente con ValidationException, que provocaba la anotacion personalizada
     * @param e
     * @return
     */
    @ExceptionHandler(ValidationException.class) // indicamos que manejara una excepcion
    @ResponseBody // indicamos que se respondera por el body
    @ResponseStatus(value= HttpStatus.BAD_REQUEST) // definimos el estado en este caso
    public ErrorMessege handlerException(ValidationException e){
        System.out.println(e);
        System.out.println(e.getMessage());
        System.out.println(e.getCause());
        System.out.println(e.getStackTrace());
        System.out.println(e.getLocalizedMessage());
        List<String> fieldErrors = new ArrayList<>();
        fieldErrors.add("The paarameter 'date' can not be null");
        return processDateField(fieldErrors);
    }

    public ErrorMessege processDateField(List<String> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>(); // para guardar los fields
        for (String fieldError: fieldErrors) {
            fields.put("date", fieldError);
        }
        return new ErrorMessege(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields); // Error messege
    }


}

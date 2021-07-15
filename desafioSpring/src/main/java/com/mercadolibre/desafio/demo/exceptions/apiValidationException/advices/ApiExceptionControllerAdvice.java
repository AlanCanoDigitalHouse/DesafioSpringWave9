package com.mercadolibre.desafio.demo.exceptions.apiValidationException.advices;

import com.mercadolibre.desafio.demo.exceptions.apiValidationException.models.ErrorMessege;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class) // escuchara un RestController, estara atento
public class ApiExceptionControllerAdvice {
    // Es un controllador que en lugar de escuchar endpoints , escucha exceptiones
    // podremos declarar metodos para manejar exceptions

    @ExceptionHandler(MethodArgumentNotValidException.class) // indicamos que manejara una excepcion
    @ResponseBody // indicamos que se respondera por el body
    @ResponseStatus(value= HttpStatus.BAD_REQUEST) // definimos el estado en este caso
    public ErrorMessege handlerException(MethodArgumentNotValidException exception){
        // escuchara los metoods de valores no validos, definidos en el dto, cada messege se mostrara por aqui

        BindingResult resutl = exception.getBindingResult(); // es un objeto de spring para validar errores
        // me da una clase BindigResult para manipular errores, me da metodos estaticos
        // Si es mas de un result, generamos una lista
        List<FieldError> fieldErrors = resutl.getFieldErrors(); // errores de campos en los dtos y getFieldErrors devuelve todos los errores de campos
        return processField(fieldErrors);
    }

    public ErrorMessege processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>(); // para guardar los fields
//        fieldErrors.forEach(fieldError -> );
        for (FieldError fieldError: fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessege(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields); // Error messege
    }
}

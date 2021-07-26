package com.mercadolibre.calculadorametroscuadrados.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ApiExcepcionControllerAdvice{

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MensajeDeError handlerArgument(MethodArgumentNotValidException exception){
        BindingResult resultado = exception.getBindingResult();
        List<FieldError> erroresDeCampo = resultado.getFieldErrors();
        return procesarCampo(erroresDeCampo);
    }

    public MensajeDeError procesarCampo(List<FieldError> fieldErrors){
        HashMap<String, String> campos = new HashMap<>();
        for (FieldError errorDeCampo : fieldErrors){
            campos.put(errorDeCampo.getField(), errorDeCampo.getDefaultMessage());
        }
        return new MensajeDeError(HttpStatus.BAD_REQUEST.value(), campos);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlerInicializar(NullPointerException exception){
        return new ResponseEntity<String>("La propiedad debe inicializarse antes de hacer cualquier operacion",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlerInicializar(BarrioNoEncontradoException exception){
        return new ResponseEntity<String>(BarrioNoEncontradoException.ERROR,HttpStatus.BAD_REQUEST);
    }

}

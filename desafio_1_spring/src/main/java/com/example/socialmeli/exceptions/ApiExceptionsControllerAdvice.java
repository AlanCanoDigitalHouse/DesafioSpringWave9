package com.example.socialmeli.exceptions;

import com.example.socialmeli.dtos.responses.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionsControllerAdvice{

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ResponseErrorDto> userNotFoundExceptionHandler(UserNotFound exception){
        ResponseErrorDto responseErrorDto = new ResponseErrorDto(HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    exception.getMessage());

        return new ResponseEntity<>(responseErrorDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOrder.class)
    public ResponseEntity<ResponseErrorDto> invalidOrderExceptionHandler(InvalidOrder exception){
        ResponseErrorDto responseErrorDto = new ResponseErrorDto(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage());

        return new ResponseEntity<>(responseErrorDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncompatibleRequest.class)
    public ResponseEntity<ResponseErrorDto> incompatibleRequestExceptionHandler(IncompatibleRequest exception){
        ResponseErrorDto responseErrorDto = new ResponseErrorDto(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getMessage());

        return new ResponseEntity<>(responseErrorDto,HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler
    public ResponseEntity<ResponseErrorDto> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception){
        ResponseErrorDto responseErrorDto = new ResponseErrorDto(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Parametro ingresado inválido");
        return new ResponseEntity<>(responseErrorDto,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<ResponseErrorDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        ResponseErrorDto responseErrorDto = new ResponseErrorDto(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Falta ingresar un parametro");
        return new ResponseEntity<>(responseErrorDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    public ErrorMessage processField( List<FieldError> fieldErrors){
        HashMap<String, String> fields = (HashMap<String, String>) fieldErrors.stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }

}
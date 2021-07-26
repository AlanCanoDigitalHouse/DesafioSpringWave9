package com.mercadolibre.calculadorametroscuadrados.exceptions;

import com.mercadolibre.calculadorametroscuadrados.dto.response.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionsControllerAdvice {

    @ExceptionHandler(LocationNotFound.class)
    public ResponseEntity<ResponseErrorDto> locationNotFoundExceptionHandler(LocationNotFound exception){
        ResponseErrorDto responseErrorDto = new ResponseErrorDto(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage());

        return new ResponseEntity<>(responseErrorDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectLocationPrice.class)
    public ResponseEntity<ResponseErrorDto> incorrectLocationPriceExceptionHandler(IncorrectLocationPrice exception){
        ResponseErrorDto responseErrorDto = new ResponseErrorDto(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage());

        return new ResponseEntity<>(responseErrorDto,HttpStatus.NOT_FOUND);
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
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            String previousMessage = fields.get(fieldError.getField());
            String newMessage = fieldError.getDefaultMessage();
            String message;
            //When many errors from same field, priorize the "empty field" one.
            if(Objects.nonNull(previousMessage) && newMessage.contains("mayúscula")){
                message = previousMessage;
            }else{
                message= newMessage;
            }
            fields.put(fieldError.getField(), message);
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }

}
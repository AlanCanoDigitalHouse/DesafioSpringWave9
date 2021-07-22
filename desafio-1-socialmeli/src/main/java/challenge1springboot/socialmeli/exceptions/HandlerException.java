package challenge1springboot.socialmeli.exceptions;

import challenge1springboot.socialmeli.exceptions.general.BadRequestException;
import challenge1springboot.socialmeli.globalconstants.Message;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class HandlerException {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBadRequestException(Exception exception){
        HashMap<String, String> message = new HashMap<>();
        message.put(exception.getClass().getSimpleName(),exception.getMessage());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), Message.WRONG_DATA_PROVIDED, message);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleGeneralInternalException(Exception exception){
        HashMap<String, String> message = new HashMap<>();
        message.put(exception.getClass().getSimpleName(),exception.getMessage());
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Error", message);
    }

    private ErrorMessage processField(List<FieldError> fieldErrors){
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validation Error", fields);
    }
}

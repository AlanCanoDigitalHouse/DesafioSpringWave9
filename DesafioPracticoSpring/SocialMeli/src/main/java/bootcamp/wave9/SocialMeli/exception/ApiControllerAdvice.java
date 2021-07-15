package bootcamp.wave9.SocialMeli.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> userNotFound(UserNotFoundException exception) {

        return new ResponseEntity<>(exception.ERROR, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> postNotFound(PostNotFoundException exception) {

        return new ResponseEntity<>(exception.ERROR, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> postNotFound(ResponseStatusException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException ex) {

        BindingResult br = ex.getBindingResult();
        List<FieldError> fieldErrors = br.getFieldErrors();

        return new ResponseEntity<>(processFields(fieldErrors), HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> processFields(List<FieldError> fields) {

        Map<String, String> result = new HashMap<>();

        fields.forEach(f -> result.put(f.getField(), f.getDefaultMessage()));

        return result;

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> invalidInputException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Invalid input.", HttpStatus.BAD_REQUEST);
    }



}

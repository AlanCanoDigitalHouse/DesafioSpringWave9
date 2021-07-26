package cl.mercadolibre.desfiotesting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice(annotations = RestController.class)
public class ApiControllersAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(DistrictNotFoundException ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(DistrictPriceDontMatchException ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

}

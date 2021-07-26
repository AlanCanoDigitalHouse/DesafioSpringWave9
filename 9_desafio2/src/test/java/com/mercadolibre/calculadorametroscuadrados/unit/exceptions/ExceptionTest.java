package com.mercadolibre.calculadorametroscuadrados.unit.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.requests.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.ResponseErrorDto;
import com.mercadolibre.calculadorametroscuadrados.exceptions.ApiExceptionsControllerAdvice;
import com.mercadolibre.calculadorametroscuadrados.exceptions.ErrorMessage;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectLocationPrice;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
public class ExceptionTest {


    @InjectMocks
    private ApiExceptionsControllerAdvice apiExceptionsControllerAdvice;

    @Test
    void locationNotFoundExceptionTest(){

        LocationNotFound locationNotFound = new LocationNotFound();
        ResponseErrorDto expectedErrorMessage =
                new ResponseErrorDto(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                        locationNotFound.getMessage());
        ResponseEntity expectedErrorResponse = new ResponseEntity<>(expectedErrorMessage, HttpStatus.NOT_FOUND);

        ResponseEntity<ResponseErrorDto> errorMessage = apiExceptionsControllerAdvice.locationNotFoundExceptionHandler(locationNotFound);

        Assertions.assertEquals(expectedErrorResponse,errorMessage);
    }

    @Test
    void incorrectLocationPriceExceptionTest(){
        IncorrectLocationPrice locationNotFound = new IncorrectLocationPrice();
        ResponseErrorDto expectedErrorMessage =
                new ResponseErrorDto(HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        locationNotFound.getMessage());
        ResponseEntity expectedErrorResponse = new ResponseEntity<>(expectedErrorMessage, HttpStatus.NOT_FOUND);

        ResponseEntity<ResponseErrorDto> errorMessage = apiExceptionsControllerAdvice.incorrectLocationPriceExceptionHandler(locationNotFound);

        Assertions.assertEquals(expectedErrorResponse,errorMessage);
    }

    @Test
    void multipleValidationErrorTest() throws JsonProcessingException {

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        BeanPropertyBindingResult beanPropertyBindingResult = new BeanPropertyBindingResult(HouseRequestDTO.class,"Error");
        beanPropertyBindingResult.addError(new FieldError("HouseRequestDto","name","El nombre de la propiedad no puede estar vacío."));
        beanPropertyBindingResult.addError(new FieldError("HouseRequestDto","rooms[0].length","El máximo largo permitido por propiedad es de 33 mts"));

        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(null,beanPropertyBindingResult);

        Map<String, String> message = new HashMap<>();
        message.put("name","El nombre de la propiedad no puede estar vacío.");
        message.put("rooms[0].length","El máximo largo permitido por propiedad es de 33 mts");
        ErrorMessage expectedMessage = new ErrorMessage(400,"Validations Error", message);

        ErrorMessage errorMessage = apiExceptionsControllerAdvice.handlerException(methodArgumentNotValidException);

        Assertions.assertEquals(writer.writeValueAsString(expectedMessage), writer.writeValueAsString(errorMessage));
    }
}

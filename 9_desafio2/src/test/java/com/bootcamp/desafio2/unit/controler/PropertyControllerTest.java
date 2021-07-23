package com.bootcamp.desafio2.unit.controler;

import com.bootcamp.desafio2.controler.PropertyController;
import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.BusinessException;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.services.IPropertyService;
import com.bootcamp.desafio2.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    @Mock
    IPropertyService propertyService;

    @InjectMocks
    PropertyController propertyController;

    @Test
    void propertyPriceWellCalculateTest() throws BusinessException, IOException, DistrictNotFoundException {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        ResponseDto responseDto = TestUtilsGenerator.getDefaultResponse();
        when(propertyService.calculatePrice(body)).thenReturn(responseDto);

        ResponseEntity<ResponseDto> response = propertyController.calculateArea(body);

        verify(propertyService, times(1)).calculatePrice(any(PropertyDto.class));
        assertAll(() -> assertEquals(responseDto, response.getBody()),
                () -> assertEquals(200, response.getStatusCodeValue()));
    }

    @Test
    void businessExceptiontest() throws IOException, BusinessException, DistrictNotFoundException {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        when(propertyService.calculatePrice(body)).thenThrow(BusinessException.class);
        assertThrows(BusinessException.class,
                () ->  propertyController.calculateArea(body));
    }

    @Test
    void ioExceptiontest() throws IOException, BusinessException, DistrictNotFoundException {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        when(propertyService.calculatePrice(body)).thenThrow(IOException.class);
        assertThrows(IOException.class,
                () ->  propertyController.calculateArea(body));
    }

    @Test
    void districtNotFoundExceptiontest() throws IOException, BusinessException, DistrictNotFoundException {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        when(propertyService.calculatePrice(body)).thenThrow(DistrictNotFoundException.class);
        assertThrows(DistrictNotFoundException.class,
                () ->  propertyController.calculateArea(body));
    }

}

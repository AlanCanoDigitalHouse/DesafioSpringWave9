package com.bootcamp.desafio2.unit.services;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.entities.District;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.BusinessException;
import com.bootcamp.desafio2.services.IDistrictService;
import com.bootcamp.desafio2.services.implementation.PropertyService;
import com.bootcamp.desafio2.util.TestUtilsGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @Mock
    IDistrictService districtService;

    @InjectMocks
    PropertyService propertyService;

    @BeforeEach @AfterEach
    void init() {
        TestUtilsGenerator.initDataBase();
        TestUtilsGenerator.appendNewDistrict(new District("Boita", 3000D));
    }

    @Test
    void propertyPriceWellCalculated() throws IOException, BusinessException, DistrictNotFoundException {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        ResponseDto expected = TestUtilsGenerator.getDefaultResponse();
        when(districtService.neighborhoodExist("Boita", 3000D)).thenReturn(true);

        ResponseDto response = propertyService.calculatePrice(body);

        verify(districtService, times(1)).neighborhoodExist("Boita", 3000D);
        assertEquals(expected, response);
    }

    @Test
    void biggerEnvironmentTest() throws IOException, BusinessException, DistrictNotFoundException {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        ResponseDto expected = TestUtilsGenerator.getDefaultResponse();
        String expectedEnvironment = body.getEnvironments().stream().max(
                Comparator.comparingDouble(o -> (o.getEnvironment_width() * o.getEnvironment_length())))
                .get().getEnvironment_name();
        when(districtService.neighborhoodExist("Boita", 3000D)).thenReturn(true);

        ResponseDto response = propertyService.calculatePrice(body);

        verify(districtService, times(1)).neighborhoodExist("Boita", 3000D);
        assertAll(() -> assertEquals(expected, response),
                () -> assertEquals(expectedEnvironment, response.getBiggerEnvironment()));

    }

    @Test
    void districtNotFoundTest() throws IOException {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        when(districtService.neighborhoodExist("Boita", 3000D)).thenReturn(false);

        assertThrows(DistrictNotFoundException.class,
                () ->  propertyService.calculatePrice(body));
        verify(districtService, times(1)).neighborhoodExist(anyString(), anyDouble());
    }

    @Test
    void propertyWithNoEnvironmentsTest() {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        body.setEnvironments(new ArrayList<>());
        assertEquals("La propiedad debe tener minimo un ambiente", assertThrows(BusinessException.class,
                () ->  propertyService.calculatePrice(body)).getMessage());
    }

    @Test
    void propertyWithNoNameTest() {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        body.setProp_name(null);
        assertEquals("La propiedad debe tener un nombre definido", assertThrows(BusinessException.class,
                () ->  propertyService.calculatePrice(body)).getMessage());
    }

    @Test
    void propertyWithNoDistrictTest() {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        body.setDistrict(null);
        assertEquals("La propiedad debe tener definido el barrio", assertThrows(BusinessException.class,
                () ->  propertyService.calculatePrice(body)).getMessage());
    }

    @Test
    void propertyWithEnvironmentErrorTest() {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        body.getEnvironments().get(0).setEnvironment_width(-1D);
        assertEquals("Los ambientes enviados tienen información incompleta o " +
                "presentan valores negativos", assertThrows(BusinessException.class,
                () ->  propertyService.calculatePrice(body)).getMessage());
    }

}

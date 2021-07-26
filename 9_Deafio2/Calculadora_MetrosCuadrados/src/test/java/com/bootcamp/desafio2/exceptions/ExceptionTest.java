package com.bootcamp.desafio2.exceptions;

import com.bootcamp.desafio2.dtos.request.HouseDTO;
import com.bootcamp.desafio2.services.IDistrictService;
import com.bootcamp.desafio2.services.implementation.PropertyService;
import com.bootcamp.desafio2.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExceptionTest {

    @InjectMocks
    PropertyService propertyService;

    @Mock
    IDistrictService districtService;



    @Test
    @DisplayName("Validacion excepction - Distrito no existente.")
    void districtNotExistsExceptionTest() throws DistrictNotExistsException, PriceNotMatchException {
        //arrange
        HouseDTO house = TestUtilsGenerator.getDefaultHouseIncorrectDistrict();
        //act
        when(propertyService.calculateArea(house)).thenThrow(DistrictNotExistsException.class);
        //assert
        Assertions.assertThrows(DistrictNotExistsException.class,
                () -> propertyService.calculateArea(house));

    }

    @Test
    @DisplayName("Validacion excepction - Precio no coincide.")
    void priceNotMatchExceptionTest() throws DistrictNotExistsException, PriceNotMatchException {
        //arrange
        HouseDTO house = TestUtilsGenerator.getDefaultHouseIncorrectPrice();
        //act
        when(propertyService.calculateArea(house)).thenThrow(PriceNotMatchException.class);
        //assert
        Assertions.assertThrows(PriceNotMatchException.class,
                () -> propertyService.calculateArea(house));
    }
}

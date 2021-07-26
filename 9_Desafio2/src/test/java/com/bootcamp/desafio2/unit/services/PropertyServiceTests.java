package com.bootcamp.desafio2.unit.services;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.services.IDistrictService;
import com.bootcamp.desafio2.services.IPropertyService;
import com.bootcamp.desafio2.services.implementation.PropertyService;
import com.bootcamp.desafio2.utils.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PropertyServiceTests {
    @Mock
    IPropertyService iPropertyService;
    @Mock
    IDistrictService districtService;
    @InjectMocks
    PropertyService propertyService;

    @Test
    @DisplayName("Verificar total de metros cuadrados")
    public void getTotalArea() throws IOException, DistrictNotFoundException {
        // arrange
        PropertyDto property = TestUtilGenerator.getProperty("Campo");
        when(districtService.neighborhoodExist(property.getDistrict().getDistrict_name(),
                property.getDistrict().getDistrict_price())).thenReturn(true);
        // act
        ResponseDto response = propertyService.calculatePrice(property);
        // assert
        verify(districtService, atLeastOnce()).neighborhoodExist(property.getDistrict().getDistrict_name(),
                property.getDistrict().getDistrict_price());
        Assertions.assertEquals(995.5, response.getTotalArea());
    }

    @Test
    @DisplayName("Verificar ambiente de mayor tamaño")
    public void getLargestEnvironment() throws IOException, DistrictNotFoundException {
        // arrange
        PropertyDto property = TestUtilGenerator.getProperty("Campo");
        when(districtService.neighborhoodExist(property.getDistrict().getDistrict_name(),
                property.getDistrict().getDistrict_price())).thenReturn(true);
        // act
        ResponseDto response = propertyService.calculatePrice(property);
        // assert
        verify(districtService, atLeastOnce()).neighborhoodExist(property.getDistrict().getDistrict_name(),
                property.getDistrict().getDistrict_price());
        Assertions.assertEquals("Villamiriam", response.getBiggerEnvironment());
    }

    @Test
    @DisplayName("Verificar total de m2 por ambiente")
    public void getm2ByEnvironment() throws IOException, DistrictNotFoundException {
        // arrange
        List<Double> m2 = TestUtilGenerator.getM2("Campo");
        PropertyDto property = TestUtilGenerator.getProperty("Campo");
        when(districtService.neighborhoodExist(property.getDistrict().getDistrict_name(),
                property.getDistrict().getDistrict_price())).thenReturn(true);
        // act
        ResponseDto response = propertyService.calculatePrice(property);
        // assert
        verify(districtService, atLeastOnce()).neighborhoodExist(property.getDistrict().getDistrict_name(),
                property.getDistrict().getDistrict_price());

        //response.getEnvironments().forEach(environment -> Assertions.assertEquals(environment.getSquareMeters(),environment.getSquareMeters()));
        for (int i = 0; i < m2.size(); i++) {
            Assertions.assertEquals(m2.get(i),response.getEnvironments().get(i).getSquareMeters());
        }
    }

    @Test
    @DisplayName("Obtener distrito inexistente")
    public void getInexistentDistrict() throws IOException {
        when(districtService.neighborhoodExist(anyString(), anyDouble())).thenThrow(IOException.class);
        //assert
        Assertions.assertThrows(IOException.class, () -> districtService.neighborhoodExist("Villa",3400D));
    }
    @Test
    @DisplayName("Distrito no encontrado")
    public void districtNotFound() throws IOException, ErrorMessage, DistrictNotFoundException {
        PropertyDto property = TestUtilGenerator.getProperty("Campo");
        when(iPropertyService.calculatePrice(any())).thenThrow(DistrictNotFoundException.class);
        // assert
        Assertions.assertThrows(DistrictNotFoundException.class, () -> iPropertyService.calculatePrice(property));
    }
    @Test
    @DisplayName("No es posible calcular")
    public void impossibleCalcule() throws IOException, ErrorMessage, DistrictNotFoundException {
        PropertyDto property = TestUtilGenerator.getProperty("Campo");
        when(iPropertyService.calculatePrice(any())).thenThrow(ErrorMessage.class);
        // assert
        Assertions.assertThrows(ErrorMessage.class, () -> iPropertyService.calculatePrice(property));
    }

}

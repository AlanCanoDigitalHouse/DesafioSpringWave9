package com.mercadolibre.calculadorametroscuadrados.unit;

import com.mercadolibre.calculadorametroscuadrados.TestUtils;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.ResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.PriceNotValidException;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Service - unit tests")
@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    DistrictRepository districtRepository;
    @InjectMocks
    CalculateServiceImpl calculateService;

    @DisplayName("calculate response")
    @Test
    public void calculateFromAValidInput() {
        HouseDTO houseDTO = TestUtils.createValidInput();
        Mockito.when(districtRepository.getPriceByDistrict(houseDTO.getDistrict_name())).thenReturn(800d);
        ResponseDTO actualResponse = calculateService.calculate(houseDTO);
        ResponseDTO expectedResponse = TestUtils.calculateValidResponse();
        Mockito.verify(districtRepository, Mockito.atLeastOnce()).getPriceByDistrict(houseDTO.getDistrict_name());
        assertEquals(24, actualResponse.getSqm());
        assertEquals(expectedResponse.getBiggest(), actualResponse.getBiggest());
        assertEquals(expectedResponse.getEnvironments().get("Primero"), actualResponse.getEnvironments().get("Primero"));
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("should throw PriceNotValidException")
    public void calculateFromAHouseWithInvalidPrice() {
        HouseDTO houseDTO = TestUtils.createInputWithInvalidPrice();
        Mockito.when(districtRepository.getPriceByDistrict(houseDTO.getDistrict_name())).thenReturn(800d);
        try{
            calculateService.calculate(houseDTO);
        }catch (Exception e){}
        Mockito.verify(districtRepository, Mockito.atLeastOnce()).getPriceByDistrict(houseDTO.getDistrict_name());
        Assertions.assertThrows(PriceNotValidException.class, () -> calculateService.calculate(houseDTO));
    }



}

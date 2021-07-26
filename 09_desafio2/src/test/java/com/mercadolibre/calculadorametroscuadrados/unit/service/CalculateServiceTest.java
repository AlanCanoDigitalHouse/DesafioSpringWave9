package com.mercadolibre.calculadorametroscuadrados.unit.service;

import com.mercadolibre.calculadorametroscuadrados.dto.Request.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.IDistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.unit.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {

    @Mock
    private IDistrictRepository districtRepository;

    @InjectMocks
    private CalculateService calculateService;

    @Test
    @DisplayName("Total Value House Calculated")
    public void totalValueWellCalculated() {
        // arrange
        HouseRequestDTO house = TestUtilsGenerator.getHouseWith3Environments("Casa 1", "Carabelas");
        DistrictDTO district = house.getDistrict();
        when(districtRepository.findByName(district.getName())).thenReturn(district);

        // act
        Double houseValue = calculateService.totalValueHouse(house);

        // assert
        verify(districtRepository, atLeastOnce()).findByName(district.getName());
        assertEquals(70000.0, houseValue);
    }

    @Test
    @DisplayName("Total Value House Calculated With Non Environment")
    public void totalValueNotEnvironmentCalculated() {
        // arrange
        HouseRequestDTO house = TestUtilsGenerator.getHouseWithoutEnvironments("Casa 1", "Carabelas");
        DistrictDTO district = house.getDistrict();
        when(districtRepository.findByName(district.getName())).thenReturn(district);

        // act
        Double houseValue = calculateService.totalValueHouse(house);

        // assert
        verify(districtRepository, atLeastOnce()).findByName(district.getName());
        assertNotNull(houseValue);
        assertEquals(0.0, houseValue);
    }

    @Test
    @DisplayName("Find Biggest House and Calculated Square Meters")
    public void biggestEnvironmentFind() {
        // arrange
        HouseRequestDTO house = TestUtilsGenerator.getHouseWith3Environments("Casa 1", "Carabelas");
        // act
        EnvironmentResponseDTO environment = calculateService.biggestEnvironment(house);
        // assert
        assertEquals(40.0, environment.getSquareFeet());
    }

    @Test
    @DisplayName("Find Biggest House With Non Environments")
    public void biggestNotEnvironmentsFind() {
        // arrange
        HouseRequestDTO house = TestUtilsGenerator.getHouseWithoutEnvironments("Casa 1", "Carabelas");

        // act &assert
        Assertions.assertThrows(NoSuchElementException.class, () ->
                calculateService.biggestEnvironment(house)
        );
    }

    @Test
    @DisplayName("Calculated All House")
    public void HouseWellCalculated() {
        // arrange
        HouseRequestDTO house = TestUtilsGenerator.getHouseWith5Environments("Casa 3", "Chapinero");
        DistrictDTO district = house.getDistrict();
        when(districtRepository.findByName(district.getName())).thenReturn(district);

        // act
        HouseResponseDTO houseResponse = calculateService.calculateHouse(house);

        // assert
        verify(districtRepository, atLeastOnce()).findByName(district.getName());
        assertEquals(285600, houseResponse.getPrice());
        assertEquals(238.0, houseResponse.getSquareFeet());
        assertNotNull(houseResponse.getBiggest());
        assertEquals(120.0, houseResponse.getBiggest().getSquareFeet());
    }

    @Test
    @DisplayName("Calculated All House With Non Environments")
    public void HouseWellCalculatedNotEnvironment() {
        // arrange
        HouseRequestDTO house = TestUtilsGenerator.getHouseWithoutEnvironments("Casa 7", "Bogota");
        DistrictDTO district = house.getDistrict();
        when(districtRepository.findByName(district.getName())).thenReturn(district);

        // act
        HouseResponseDTO houseResponse = calculateService.calculateHouse(house);

        // assert
        verify(districtRepository, atLeastOnce()).findByName(district.getName());
        assertEquals(0D, houseResponse.getSquareFeet());
        assertNull(houseResponse.getBiggest());
    }


}

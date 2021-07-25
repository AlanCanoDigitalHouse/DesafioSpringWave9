package com.mercadolibre.calculadorametroscuadrados.units.service;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.environmentValidationException.exceptions.DataBaseNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.exceptions.environmentValidationException.exceptions.NotFoundEnvironmentException;
import com.mercadolibre.calculadorametroscuadrados.models.EnvironmentModel;
import com.mercadolibre.calculadorametroscuadrados.repository.IEnvironmentRepository;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.units.util.UtilTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("Unit Test - CalculateService")
@ExtendWith(MockitoExtension.class)
public class CalculateServiceUnitTest {

    @Mock
    IEnvironmentRepository environmentRepository;

    @InjectMocks
    CalculateService calculateService;

    List<EnvironmentDTO> environments = new ArrayList<>();
    UtilTest utilTest;


    @BeforeEach
    @AfterEach
    private void setUp() throws DataBaseNotFoundException {
        utilTest = new UtilTest();
        environments = UtilTest.loadValidEnvironments();
    }

    @Test
    @DisplayName("Unit Test - method: validatePropertyName - 1")
    void validatePropertyName_1(){
        // arrange
        Optional<EnvironmentModel> expected = Optional.of(new EnvironmentModel("Belgrano"));
        when(environmentRepository.findEnvironment("Belgrano")).thenReturn(expected);
        // act
        Optional<EnvironmentModel> current = environmentRepository.findEnvironment("Belgrano");
        // assert
        verify(environmentRepository, atLeastOnce()).findEnvironment(Mockito.anyString());
        assertEquals(expected,current);
    }

    @Test
    @DisplayName("Unit Test - method: validatePropertyName - 1")
    void validatePropertyName_2(){
        // arrange
        Optional<EnvironmentModel> expected = Optional.of(new EnvironmentModel("Belgrano"));
        // act
        when(environmentRepository.findEnvironment("Belgrano")).thenReturn(expected);
        Optional<EnvironmentModel> current = environmentRepository.findEnvironment("Belgrano");
        // assert
        verify(environmentRepository, atLeastOnce()).findEnvironment(Mockito.anyString());
        assertEquals(expected,current);
    }


    @Test
    @DisplayName("Unit Test - method: calculate - 1")
    void calculate(){
        // arrange
        // dato de entrada
        HouseDTO houseDTO = UtilTest.loadHouseDTO("Test","Belgrano");
        HouseResponseDTO expected = UtilTest.loadHouseResponseDTO(houseDTO);

        // act
        when(environmentRepository.findEnvironment("Belgrano")).thenReturn(Optional.of(new EnvironmentModel("Belgrano")));
        HouseResponseDTO current = calculateService.calculate(houseDTO);

        // assert
        verify(environmentRepository, atMostOnce()).findEnvironment(Mockito.anyString());
        assertEquals(expected,current);

    }



    @Test
    @DisplayName("Unit Test - method: calculate - 2")
    void calculate_2(){
        // arrange
        HouseDTO houseDTO = UtilTest.loadHouseDTO("Belgrano","NotFound");

        // action and assert
        when(environmentRepository.findEnvironment("NotFound")).thenReturn(Optional.empty());
        assertThrows(NotFoundEnvironmentException.class,() -> calculateService.calculate(houseDTO));

    }


    @Test
    @DisplayName("Unit Test - method: calculatePrice - 1")
    void calculatePrice_1(){
        // arrange
        Double expected = 1124000.0;
        Double squareFeet = 281.0;
        Double district_price = 4000.0;

        // act
        Double current = calculateService.calculatePrice(squareFeet,district_price);

        // assert
        assertEquals(expected,current);
    }

    @Test
    @DisplayName("Unit Test - method: calculatePrice - 2")
    void calculatePrice_2(){
        // arrange
        Double expected = 464800.0;
        Double squareFeet = 581.0;
        Double district_price = 800.0;

        // act
        Double current = calculateService.calculatePrice(squareFeet,district_price);

        // assert
        assertEquals(expected,current);
    }

    @Test
    @DisplayName("Unit Test - method: calculatePrice - 3")
    void calculatePrice_3(){
        // arrange
        Double expected = 1104010.0;
        Double squareFeet = 281.0;
        Double district_price = 4000.0;

        // act
        Double current = calculateService.calculatePrice(squareFeet,district_price);

        // assert
        Assertions.assertNotEquals(current, expected);
    }

}




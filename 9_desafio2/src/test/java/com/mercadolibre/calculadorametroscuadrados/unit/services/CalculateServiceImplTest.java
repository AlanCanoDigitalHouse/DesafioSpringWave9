package com.mercadolibre.calculadorametroscuadrados.unit.services;

import com.mercadolibre.calculadorametroscuadrados.dtos.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectDistrictPriceException;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.services.CalculateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculateServiceImplTest {

    @Mock
    DistrictRepository repository;

    @InjectMocks
    CalculateServiceImpl service;

    HouseDTO validHouse = new HouseDTO();

    @BeforeEach
    void init() {

        ArrayList<EnvironmentDTO> environments = new ArrayList<>();
        DistrictDTO validDistrict = new DistrictDTO("Belgrano", 1100.0);

        environments.add(new EnvironmentDTO("Environment 1", 2.0, 2.0));
        environments.add(new EnvironmentDTO("Environment 2", 9.1, 3.0));
        environments.add(new EnvironmentDTO("Environment 3", 4.0, 4.0));

        validHouse.setProp_name("ValidHouse");
        validHouse.setDistrict(validDistrict);
        validHouse.setEnvironments(environments);

    }

    @Test
    @DisplayName("Correct Total Square Feet")
    void testCorrectTotalSquareFeet() throws DistrictNotFoundException, IncorrectDistrictPriceException {

        //Arrange
        Double expected = validHouse.getEnvironments().stream().mapToDouble(e -> e.getEnvironment_length() * e.getEnvironment_width()).sum();
        when(repository.findPriceDistrict(any())).thenReturn(1100.0);//Correct Belgrano 1100.0
        //Act & Assert
        assertEquals(expected, service.calculate(validHouse).getSquareFeet());
        verify(repository, atLeast(1)).findPriceDistrict(any());
    }

    @Test
    @DisplayName("Correct Total Price Correct")
    void testCorrectTotalPrice() throws DistrictNotFoundException, IncorrectDistrictPriceException {

        //Arrange
        Double totalHouseSquareFeet = validHouse.getEnvironments().stream().mapToDouble(e -> e.getEnvironment_length() * e.getEnvironment_width()).sum();
        Double totalPrice = totalHouseSquareFeet * validHouse.getDistrict().getDistrict_price();

        when(repository.findPriceDistrict(any())).thenReturn(1100.0); //Correct Belgrano 1100.0

        //Act & Assert
        assertEquals(totalPrice, service.calculate(validHouse).getPrice());
        verify(repository, atLeast(1)).findPriceDistrict(any());
    }

    @Test
    @DisplayName("Correct Biggest Environment")
    void testCorrectBiggestEnvironment() throws DistrictNotFoundException, IncorrectDistrictPriceException {

        //Arrange
        EnvironmentDTO biggest = validHouse.getEnvironments().stream().max(Comparator.comparing(e -> e.getEnvironment_length() * e.getEnvironment_width())).get();

        when(repository.findPriceDistrict(any())).thenReturn(1100.0); //Correct Belgrano 1100.0

        //Act & Assert
        assertEquals(biggest, service.calculate(validHouse).getBiggest());
        verify(repository, atLeast(1)).findPriceDistrict(any());
    }


    @Test
    @DisplayName("InCorrect District Price")
    void inputInCorrectPriceForExistingDistrict() throws DistrictNotFoundException {
        //Arrange
        DistrictDTO district = validHouse.getDistrict(); //Correct Belgrano 1100.0

        when(repository.findPriceDistrict(district)).thenReturn(900.7); //Correct Belgrano 1100.0

        //Act & Assert
        assertThrows(IncorrectDistrictPriceException.class, () -> service.calculate(validHouse));
        verify(repository, atLeast(1)).findPriceDistrict(any());
    }

}


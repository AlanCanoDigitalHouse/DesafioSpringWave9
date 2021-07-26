package com.mercadolibre.calculadorametroscuadrados.unit.services;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import com.mercadolibre.calculadorametroscuadrados.utils.DataGeneratorTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {
    @Mock
    PriceRepositoryImpl priceRepository;

    @InjectMocks
    CalculateService calculateService;

    @Test
    @DisplayName("Test: validacion de la respuesta completa")
    public void calculateSuccessOutputTest(){
        //Arrange
        HouseDTO houseTest = DataGeneratorTest.getCorrectDataHouse("House Test");
        HouseResponseDTO expectedOutput = new HouseResponseDTO(houseTest);
        expectedOutput.setSquareFeet(71.0);
        expectedOutput.setPrice(71*houseTest.getDistrict_price());
        expectedOutput.setBiggest(new RoomDTO("Garage", 7.0, 4.0));

        //Act
        when(priceRepository.findDistrictByName(houseTest.getDistrict_name())).thenReturn(true);
        HouseResponseDTO outputToTest = calculateService.calculate(houseTest);
        verify(priceRepository, atLeast(1)).findDistrictByName(houseTest.getDistrict_name());

        //Assert
        Assertions.assertEquals(expectedOutput, outputToTest);
    }

    @Test
    @DisplayName("Test: validacion de la respuesta completa con coleccion de rooms vacia.")
    public void calculateSuccessOutputTestWithoutRooms(){
        //Arrange
        HouseDTO houseTest = DataGeneratorTest.getCorrectDataHouse("House Test");
        HouseResponseDTO expectedOutput = new HouseResponseDTO(houseTest);
        houseTest.setRooms(new ArrayList<>());
        expectedOutput.setSquareFeet(0.0);
        expectedOutput.setPrice(0.0);
        expectedOutput.setBiggest(null);

        //Act
        when(priceRepository.findDistrictByName(houseTest.getDistrict_name())).thenReturn(true);
        HouseResponseDTO outputToTest = calculateService.calculate(houseTest);
        verify(priceRepository, atLeast(1)).findDistrictByName(houseTest.getDistrict_name());

        //Assert
        Assertions.assertEquals(expectedOutput, outputToTest);
    }

    @Test
    @DisplayName("Test: validacion del precio calculado.")
    public void calculateHousePrice(){
        //Arrange
        HouseDTO houseTest = DataGeneratorTest.getCorrectDataHouse("House Test");

        //Act
        when(priceRepository.findDistrictByName(houseTest.getDistrict_name())).thenReturn(true);
        HouseResponseDTO outputToTest = calculateService.calculate(houseTest);
        verify(priceRepository, atLeast(1)).findDistrictByName(houseTest.getDistrict_name());

        //Assert
        Assertions.assertEquals(71000.0, outputToTest.getPrice());
    }

    @Test
    @DisplayName("Test: validacion ambiente de mayor tamaño.")
    public void calculateBiggestRoom(){
        //Arrange
        HouseDTO houseTest = DataGeneratorTest.getCorrectDataHouse("House Test");
        RoomDTO biggestRoom = new RoomDTO("Garage", 7.0, 4.0);

        //Act
        when(priceRepository.findDistrictByName(houseTest.getDistrict_name())).thenReturn(true);
        HouseResponseDTO outputToTest = calculateService.calculate(houseTest);
        verify(priceRepository, atLeast(1)).findDistrictByName(houseTest.getDistrict_name());

        //Assert
        Assertions.assertEquals(biggestRoom, outputToTest.getBiggest());
    }

    @Test
    @DisplayName("Test: validacion metros cuadrados por ambiente.")
    public void calculateSquareFeet(){
        //Arrange
        RoomDTO biggestRoom = new RoomDTO("Garage", 7.0, 4.0);

        //Act
        Double squareFeet = biggestRoom.getSquareFeet();

        //Assert
        Assertions.assertEquals(28, squareFeet);
    }
}

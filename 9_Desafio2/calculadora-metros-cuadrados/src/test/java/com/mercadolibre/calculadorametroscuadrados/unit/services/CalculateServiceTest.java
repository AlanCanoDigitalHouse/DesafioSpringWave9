package com.mercadolibre.calculadorametroscuadrados.unit.services;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.utils.testUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {
    @Mock
    PriceRepositoryImpl priceRepository;

    @InjectMocks
    CalculateService calculateService;

    @Test
    void calculateSquareMetersHouse1Room() {
        //arrange
        HouseDTO houseDTO = testUtils.generate1RoomHouse();

        //act
        HouseResponseDTO result = calculateService.calculate(houseDTO);
        Integer expected = testUtils.generate1RoomHouseSquareMeters();

        //assert
        Mockito.verify(priceRepository, Mockito.atLeast(1)).findPriceLocation(houseDTO.getAddress().getLocation());
        Assertions.assertEquals(expected, result.getSquareFeet());
    }

    @Test
    void calculateSquareMetersHouse3Rooms() {
        //arrange
        HouseDTO houseDTO = testUtils.generate3RoomsHouse();

        //act
        HouseResponseDTO result = calculateService.calculate(houseDTO);
        Integer expected = testUtils.generate3RoomsHouseSquareMeters();

        //assert
        Mockito.verify(priceRepository, Mockito.atLeast(1)).findPriceLocation(houseDTO.getAddress().getLocation());
        Assertions.assertEquals(expected, result.getSquareFeet());
    }

    @Test
    void calculateSquareMetersHouseNoRooms() {
        //arrange
        HouseDTO houseDTO = testUtils.generateNoRoomsHouse();

        //act
        HouseResponseDTO result = calculateService.calculate(houseDTO);
        Integer expected = 0;

        //assert
        Mockito.verify(priceRepository, Mockito.atLeast(1)).findPriceLocation(houseDTO.getAddress().getLocation());
        Assertions.assertEquals(expected, result.getSquareFeet());
    }

    @Test
    void calculateSquareMetersHouseNullRooms() {
        //arrange
        HouseDTO houseDTO = testUtils.generateNullRoomsHouse();

        //act
        HouseResponseDTO result = calculateService.calculate(houseDTO);
        Integer expected = 0;

        //assert
        Mockito.verify(priceRepository, Mockito.atLeast(1)).findPriceLocation(houseDTO.getAddress().getLocation());
        Assertions.assertEquals(expected, result.getSquareFeet());
    }

    @Test
    void calculateNullHouse() {
        //arrange
        HouseDTO houseDTO = testUtils.generateNullHouse();

        //act && assert
        Assertions.assertThrows(NullPointerException.class, () -> calculateService.calculate(houseDTO));

    }

    @Test
    void shouldReturnBiggestRoom() {
        //arrange
        HouseDTO houseDTO = testUtils.generate3RoomsHouse();
        RoomDTO expected = testUtils.generateBiggestRoomFor3RoomsHouse();

        //act
        HouseResponseDTO result = calculateService.calculate(houseDTO);

        //assert
        Mockito.verify(priceRepository, Mockito.atLeast(1)).findPriceLocation(houseDTO.getAddress().getLocation());
        Assertions.assertEquals(expected, result.getBiggest());
    }
}

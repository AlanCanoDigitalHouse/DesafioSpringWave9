package com.desafios.test.unit.services;

import com.desafios.test.dtos.DistrictDTO;
import com.desafios.test.dtos.EnvironmentDTO;
import com.desafios.test.dtos.request.HouseRequestDTO;
import com.desafios.test.dtos.response.HouseResponseDTO;
import com.desafios.test.exceptions.NoDistrictFoundException;
import com.desafios.test.repositories.CalculateRepository;
import com.desafios.test.services.CalculateServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {
    @Mock
    CalculateRepository repository;

    @InjectMocks
    CalculateServiceImpl service;

    @Test
    @DisplayName("Service - Test calculating total house square feet for house with one room")
    public void calculateTotalHouseSquareFeetForHouseWithOneRoomTest() {
        // arrange
        HouseRequestDTO house = setHouse();
        addTestRoom(house, "Dormitorio principal", 5.0, 5.0);
        // act
        HouseResponseDTO response = service.calculateHouseSquareFeet(house);
        // assert
        assertEquals( 25.0, response.getSquareFeet());
    }

    @Test
    @DisplayName("Service - Test calculating total house square feet for house with multiple rooms")
    public void calculateTotalHouseTotalSquareFeetForHouseWithMultipleRoomsTest() {
        // arrange
        HouseRequestDTO house = setHouse();
        addFourTestRooms(house);
        // act
        HouseResponseDTO response = service.calculateHouseSquareFeet(house);
        // assert
        assertEquals( 59.0, response.getSquareFeet());
    }

    @Test
    @DisplayName("Service - Test calculating biggest room for a house with one room")
    public void calculateBiggestRoomForHouseWithOneRoomTest() {
        // arrange
        HouseRequestDTO house = setHouse();
        addTestRoom(house, "Cocina", 3.5, 4.0);
        EnvironmentDTO biggest = house.getEnvironments().get(0);
        // act
        HouseResponseDTO response = service.calculateBiggestRoom(house);
        // assert
        assertEquals(biggest, response.getBiggest());
    }

    @Test
    @DisplayName("Service - Test calculating biggest room for a house with multiple rooms")
    public void calculateBiggestRoomForHouseWithMultipleRoomsTest() {
        // arrange
        HouseRequestDTO house = setHouse();
        addFourTestRooms(house);
        EnvironmentDTO biggest = house.getEnvironments().get(0);
        // act
        HouseResponseDTO response = service.calculateBiggestRoom(house);
        // assert
        assertEquals(biggest, response.getBiggest());
    }

    @Test
    @DisplayName("Service - Test calculating total house price for a house with one room")
    public void calculateTotalHousePriceForHouseWithOneRoomTest() {
        // arrange
        HouseRequestDTO house = setHouse();
        addTestRoom(house, "Monoambiente", 6.0, 5.0);
        DistrictDTO district = new DistrictDTO("Palermo", 1000.0);
        when(repository.findPriceDistrict(district.getDistrict_name())).thenReturn(district);
        // act
        HouseResponseDTO response = service.calculatePrice(house);
        //assert
        verify(repository, atLeastOnce()).findPriceDistrict(district.getDistrict_name());
        assertEquals( district.getDistrict_price() * 30, response.getPrice());
    }

    @Test
    @DisplayName("Service - Test calculating total house price for a house with multiple rooms")
    public void calculateTotalHousePriceForHouseWithMultipleRoomsTest() {
        // arrange
        HouseRequestDTO house = setHouse();
        addFourTestRooms(house);
        DistrictDTO district = new DistrictDTO("Palermo", 1000.0);
        when(repository.findPriceDistrict(district.getDistrict_name())).thenReturn(district);
        // act
        HouseResponseDTO response = service.calculatePrice(house);
        //assert
        verify(repository, atLeastOnce()).findPriceDistrict(district.getDistrict_name());
        assertEquals( district.getDistrict_price() * 59, response.getPrice());
    }

    @Test
    @DisplayName("Service - Test calculating total house price with no existing district")
    public void calculateTotalHousePriceNoDistrictTest() {
        // arrange
        NoDistrictFoundException noDistrictException = new NoDistrictFoundException();
        DistrictDTO district = new DistrictDTO("Sin barrio", 1000.0);
        when(repository.findPriceDistrict(district.getDistrict_name())).thenThrow(noDistrictException);
        //act && assert
        assertThrows(NoDistrictFoundException.class, () -> repository.findPriceDistrict(district.getDistrict_name()));
        verify(repository, atLeastOnce()).findPriceDistrict(district.getDistrict_name());
    }

    @Test
    @DisplayName("Service - Test calculating rooms square feet for a house with one room")
    public void calculateRoomsSquareFeetForHouseHouseWithOneRoomTest() {
        // arrange
        HouseRequestDTO house = setHouse();
        addTestRoom(house, "Dormitorio principal", 5.0, 5.0);
        // act
        HouseResponseDTO response = service.calculateRoomSquareFeet(house);
        // assert
        assertEquals( 25.0, response.getEnvironments().get(0).getSquareFeet());
    }

    @Test
    @DisplayName("Service - Test calculating rooms square feet for a house with multiple rooms")
    public void calculateRoomsSquareFeetForHouseWithMultipleRoomsTest() {
        // arrange
        HouseRequestDTO house = setHouse();
        addFourTestRooms(house);
        // act
        HouseResponseDTO response = service.calculateRoomSquareFeet(house);
        // assert
        assertEquals( 25.0, response.getEnvironments().get(0).getSquareFeet());
        assertEquals( 14.0, response.getEnvironments().get(1).getSquareFeet());
        assertEquals( 15.0, response.getEnvironments().get(2).getSquareFeet());
        assertEquals( 5.0, response.getEnvironments().get(3).getSquareFeet());
    }

    private HouseRequestDTO setHouse() {
        return new HouseRequestDTO("La campesina", new DistrictDTO("Palermo", 1000.0), new ArrayList<>());
    }

    private void addFourTestRooms(HouseRequestDTO house) {
        addTestRoom(house, "Dormitorio principal", 5.0, 5.0);
        addTestRoom(house, "Cocina", 3.5, 4.0);
        addTestRoom(house, "Living comerdor", 5.0, 3.0);
        addTestRoom(house, "Escritorio", 2.0, 2.5);
    }

    private void addTestRoom(HouseRequestDTO house, String name, Double width, Double length) {
        List<EnvironmentDTO> environments = house.getEnvironments();
        environments.add(new EnvironmentDTO(name, width, length));
        house.setEnvironments(environments);
    }
}
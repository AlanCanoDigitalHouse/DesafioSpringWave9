package com.desafios.test.unit.controllers;

import com.desafios.test.controllers.CalculateController;
import com.desafios.test.dtos.DistrictDTO;
import com.desafios.test.dtos.EnvironmentDTO;
import com.desafios.test.dtos.request.HouseRequestDTO;
import com.desafios.test.dtos.response.HouseResponseDTO;
import com.desafios.test.exceptions.NoDistrictFoundException;
import com.desafios.test.services.CalculateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateControllerTest {
    @Mock
    CalculateService service;

    @InjectMocks
    CalculateController controller;

    @Test
    @DisplayName("Controller - Test calculating total house square feet for house with one room")
    public void totalSquareFeetForHouseWithOneRoomTest() {
        //arrange
        HouseRequestDTO house = setHouse();
        addTestRoom(house, "Monoambiente", 6.0, 5.0);
        HouseResponseDTO houseResponse = new HouseResponseDTO(house);
        houseResponse.setSquareFeet(30.0);
        when(service.calculateHouseSquareFeet(house)).thenReturn(houseResponse);
        //act
        controller.squareFeet(house);
        //assert
        verify(service, atLeastOnce()).calculateHouseSquareFeet(house);
    }

    @Test
    @DisplayName("Controller - Test calculating total house square feet for house with multiple rooms")
    public void totalSquareFeetForHouseWithMultipleRoomsTest() {
        //arrange
        HouseRequestDTO house = setHouse();
        addFourTestRooms(house);
        HouseResponseDTO houseResponse = new HouseResponseDTO(house);
        houseResponse.setSquareFeet(59.0);
        when(service.calculateHouseSquareFeet(house)).thenReturn(houseResponse);
        //act
        controller.squareFeet(house);
        //assert
        verify(service, atLeastOnce()).calculateHouseSquareFeet(house);
    }

    @Test
    @DisplayName("Controller - Test calculating total house price for a house with one room")
    public void totalHousePriceForHouseWithOneRoomTest() {
        //arrange
        HouseRequestDTO house = setHouse();
        addTestRoom(house, "Monoambiente", 5.0, 6.0);
        HouseResponseDTO houseResponse = new HouseResponseDTO(house);
        houseResponse.setPrice(30000.0);
        when(service.calculatePrice(house)).thenReturn(houseResponse);
        //act
        controller.price(house);
        //assert
        verify(service, atLeastOnce()).calculatePrice(house);
    }

    @Test
    @DisplayName("Controller - Test calculating total house price for a house with multiple")
    public void totalHousePriceForHouseWithMultipleRoomsTest() {
        //arrange
        HouseRequestDTO house = setHouse();
        addFourTestRooms(house);
        HouseResponseDTO houseResponse = new HouseResponseDTO(house);
        houseResponse.setPrice(59000.0);
        when(service.calculatePrice(house)).thenReturn(houseResponse);
        //act
        controller.price(house);
        //assert
        verify(service, atLeastOnce()).calculatePrice(house);
    }

    @Test
    @DisplayName("Controller - Test calculating total house price with no existing district")
    public void calculateTotalHousePriceNoDistrictTest() {
        // arrange
        HouseRequestDTO house = setHouse();
        addTestRoom(house, "Monoambiente", 5.0, 6.0);
        NoDistrictFoundException noDistrictException = new NoDistrictFoundException();
        when(service.calculatePrice(house)).thenThrow(noDistrictException);
        //act && assert
        assertThrows(NoDistrictFoundException.class, () -> service.calculatePrice(house));
        verify(service, atLeastOnce()).calculatePrice(house);
    }

    @Test
    @DisplayName("Controller - Test calculating house biggest room for a house with one room")
    public void biggestRoomForHouseWithOneRoomTest() {
        //arrange
        HouseRequestDTO house = setHouse();
        addTestRoom(house, "Monoambiente", 6.0, 5.0);
        HouseResponseDTO houseResponse = new HouseResponseDTO(house);
        houseResponse.setBiggest(new EnvironmentDTO("Monoambiente", 6.0, 5.0));
        when(service.calculateBiggestRoom(house)).thenReturn(houseResponse);
        //act
        controller.biggestRoom(house);
        //assert
        verify(service, atLeastOnce()).calculateBiggestRoom(house);
    }

    @Test
    @DisplayName("Controller - Test calculating house biggest room for a house with multiple rooms")
    public void biggestRoomForHouseWithMultipleRoomsTest() {
        //arrange
        HouseRequestDTO house = setHouse();
        addFourTestRooms(house);
        HouseResponseDTO houseResponse = new HouseResponseDTO(house);
        houseResponse.setBiggest(new EnvironmentDTO("Dormitorio principal", 5.0, 5.0));
        when(service.calculateBiggestRoom(house)).thenReturn(houseResponse);
        //act
        controller.biggestRoom(house);
        //assert
        verify(service, atLeastOnce()).calculateBiggestRoom(house);
    }

    @Test
    @DisplayName("Controller - Test calculating house rooms square feet for a house with one room")
    public void roomSquareFeetForHouseWithOneRoomTest() {
        //arrange
        HouseRequestDTO house = setHouse();
        addTestRoom(house, "Monoambiente", 6.0, 5.0);
        HouseResponseDTO houseResponse = new HouseResponseDTO(house);
        for(EnvironmentDTO room: house.getEnvironments())
            room.getSquareFeet();
        houseResponse.setEnvironments(house.getEnvironments());
        when(service.calculateRoomSquareFeet(house)).thenReturn(houseResponse);
        //act
        controller.roomSquareFeet(house);
        //assert
        verify(service, atLeastOnce()).calculateRoomSquareFeet(house);
    }

    @Test
    @DisplayName("Controller - Test calculating house rooms square feet for a house with multiple rooms")
    public void roomSquareFeetForHouseWithMultipleRoomsTest() {
        //arrange
        HouseRequestDTO house = setHouse();
        addFourTestRooms(house);
        HouseResponseDTO houseResponse = new HouseResponseDTO(house);
        for(EnvironmentDTO room: house.getEnvironments())
            room.getSquareFeet();
        houseResponse.setEnvironments(house.getEnvironments());
        when(service.calculateRoomSquareFeet(house)).thenReturn(houseResponse);
        //act
        controller.roomSquareFeet(house);
        //assert
        verify(service, atLeastOnce()).calculateRoomSquareFeet(house);
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


package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.responses.HouseResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculateServiceTest {

    @Test
    void propertyPriceHappyPath() {
        CalculateService classService = new CalculateService();
        HouseResponseDTO houseResponseDTO = new HouseResponseDTO();
        HouseDTO houseDTO = new HouseDTO();
        RoomDTO aRoom = new RoomDTO();
        houseDTO.setRooms(new ArrayList<>());

        aRoom.setEnvironment_length(25.0);
        aRoom.setEnvironment_width(25.0);

        houseDTO.setDistrict_price(1.0);
        houseDTO.getRooms().add(aRoom);

        classService.propertyPrice(houseDTO,houseResponseDTO);

        assertEquals(625.0 , houseResponseDTO.getPrice());

    }
    @Test
    void propertyPriceHappyPath2() {
        CalculateService classService = new CalculateService();
        HouseResponseDTO houseResponseDTO = new HouseResponseDTO();
        HouseDTO houseDTO = new HouseDTO();
        RoomDTO aRoom = new RoomDTO();
        houseDTO.setRooms(new ArrayList<>());

        aRoom.setEnvironment_length(20.0);
        aRoom.setEnvironment_width(20.0);

        houseDTO.setDistrict_price(1.0);
        houseDTO.getRooms().add(aRoom);

        classService.propertyPrice(houseDTO,houseResponseDTO);

        assertEquals(400.0 , houseResponseDTO.getPrice());

    }

    @Test
    void propertySize() {
        CalculateService classService = new CalculateService();
        HouseResponseDTO houseResponseDTO = new HouseResponseDTO();
        HouseDTO houseDTO = new HouseDTO();
        RoomDTO aRoom = new RoomDTO();
        houseDTO.setRooms(new ArrayList<>());

        aRoom.setEnvironment_length(25.0);
        aRoom.setEnvironment_width(25.0);

        houseDTO.getRooms().add(aRoom);

        classService.propertySize(houseDTO,houseResponseDTO);

        assertEquals(625.0 , houseResponseDTO.getSquareFeet());
    }
    @Test
    void propertySize2() {
        CalculateService classService = new CalculateService();
        HouseResponseDTO houseResponseDTO = new HouseResponseDTO();
        HouseDTO houseDTO = new HouseDTO();
        RoomDTO aRoom = new RoomDTO();
        houseDTO.setRooms(new ArrayList<>());

        aRoom.setEnvironment_length(20.0);
        aRoom.setEnvironment_width(20.0);

        houseDTO.getRooms().add(aRoom);

        classService.propertySize(houseDTO,houseResponseDTO);

        assertEquals(400.0 , houseResponseDTO.getSquareFeet());
    }

    @Test
    void biggestProperty() {
        CalculateService classService = new CalculateService();
        HouseResponseDTO houseResponseDTO = new HouseResponseDTO();
        HouseDTO houseDTO = new HouseDTO();
        RoomDTO aRoom = new RoomDTO();
        houseDTO.setRooms(new ArrayList<>());

        aRoom.setEnvironment_length(25.0);
        aRoom.setEnvironment_width(25.0);
        houseDTO.getRooms().add(aRoom);

        classService.biggestProperty(houseDTO,houseResponseDTO);
        Boolean res = aRoom.equals(houseResponseDTO.getBiggest());

        assertTrue(res);
    }
    @Test
    void biggestPropertyWith2Rooms() {
        CalculateService classService = new CalculateService();
        HouseResponseDTO houseResponseDTO = new HouseResponseDTO();
        HouseDTO houseDTO = new HouseDTO();
        RoomDTO aRoom = new RoomDTO();
        houseDTO.setRooms(new ArrayList<>());

        aRoom.setEnvironment_length(25.0);
        aRoom.setEnvironment_width(25.0);
        houseDTO.getRooms().add(aRoom);


        RoomDTO smallRoom = new RoomDTO();
        smallRoom.setEnvironment_length(15.0);
        smallRoom.setEnvironment_width(15.0);
        houseDTO.getRooms().add(aRoom);

        classService.biggestProperty(houseDTO,houseResponseDTO);
        Boolean res = aRoom.equals(houseResponseDTO.getBiggest());

        assertTrue(res);
    }

    @Test
    void mapResultado() {
        CalculateService classService = new CalculateService();
        HouseDTO houseDTO = new HouseDTO();
        RoomDTO aRoom = new RoomDTO();
        houseDTO.setRooms(new ArrayList<>());

        houseDTO.setDistrict_price(1.0);


        aRoom.setEnvironment_length(25.0);
        aRoom.setEnvironment_width(25.0);
        houseDTO.getRooms().add(aRoom);

        HouseResponseDTO houseResponseDTO = classService.mapResultado(houseDTO);

        boolean res = houseResponseDTO.getBiggest().equals(aRoom) || houseResponseDTO.getPrice().equals(625.0) || houseResponseDTO.getSquareFeet().equals(625.0);

        assertTrue(res);
    }

    @Test
    void calculateM2test() {
        CalculateService classService = new CalculateService();
        RoomDTO aRoom = new RoomDTO();
        aRoom.setEnvironment_length(25.0);
        aRoom.setEnvironment_width(25.0);


        assertEquals (625.0, classService.calculateM2(aRoom) );
    }
    @Test
    void calculateM2test2() {
        CalculateService classService = new CalculateService();
        RoomDTO aRoom = new RoomDTO();
        aRoom.setEnvironment_length(20.0);
        aRoom.setEnvironment_width(20.0);


        assertEquals (400.0, classService.calculateM2(aRoom) );
    }






}
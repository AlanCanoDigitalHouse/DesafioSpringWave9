package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculateService {
    public HouseResponseDTO calculate(HouseDTO house) {
        HouseResponseDTO response = new HouseResponseDTO(house);
        calculateRoomSquareFeet(house, response);
        response.setPrice(calculatePrice(response.getSquareFeet()));
        return response;
    }

    private void calculateRoomSquareFeet(HouseDTO house, HouseResponseDTO response) {
        double totalSquareFeet = 0;
        RoomDTO biggest = null;
        double maxRoom = 0;
        for (RoomDTO room : house.getEnvironments()) {
            double squareFeet = room.getSquareFeet();
            totalSquareFeet += squareFeet;
            if (biggest == null || squareFeet > maxRoom) {
                biggest = room;
                maxRoom = squareFeet;
            }
        }
        response.setSquareFeet(totalSquareFeet);
        response.setBiggest(biggest);
    }

    private double calculatePrice(double result) {
        return result * 800;
    }
}

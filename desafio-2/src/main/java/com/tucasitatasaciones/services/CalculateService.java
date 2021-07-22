package com.tucasitatasaciones.services;

import com.tucasitatasaciones.DTOs.PropertyDTO;
import com.tucasitatasaciones.DTOs.PropertyResponseDTO;
import com.tucasitatasaciones.DTOs.PriceDTO;
import com.tucasitatasaciones.DTOs.EnvironmentDTO;
import com.tucasitatasaciones.repositories.PriceRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    PriceRepositoryImpl priceRepository;

    public CalculateService(PriceRepositoryImpl priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PropertyResponseDTO calculate(PropertyDTO house) {
        PropertyResponseDTO response = new PropertyResponseDTO(house);
        calculateRoomSquareFeet(house, response);
        response.setPrice(calculatePrice(response.getSquareFeet()));
        return response;
    }

    private void calculateRoomSquareFeet(PropertyDTO house, PropertyResponseDTO response) {
        double totalSquareFeet = 0;
        EnvironmentDTO biggest = null;
        double maxRoom = 0;
        for (EnvironmentDTO room : house.getEnvironments()) {
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

    public PriceDTO probarMapper(String location) {
        return priceRepository.findPriceLocation(location);
    }
}

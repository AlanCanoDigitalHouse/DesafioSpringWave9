package com.tucasitatasaciones.services;

import com.tucasitatasaciones.DTOs.PropertyDTO;
import com.tucasitatasaciones.DTOs.PropertyResponseDTO;
import com.tucasitatasaciones.DTOs.PriceDTO;
import com.tucasitatasaciones.DTOs.EnvironmentDTO;
import com.tucasitatasaciones.exceptions.DistrictBadRequestException;
import com.tucasitatasaciones.globalconstants.Message;
import com.tucasitatasaciones.repositories.PriceRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CalculateService {

    PriceRepositoryImpl priceRepository;

    public CalculateService(PriceRepositoryImpl priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PropertyResponseDTO calculate(PropertyDTO house) {
        validateDistrictData(house.getDistrict());
        PropertyResponseDTO response = new PropertyResponseDTO(house);
        calculateRoomSquareFeet(house, response);
        response.setPrice(calculatePrice(response.getSquareFeet(), response.getDistrict().getDistrict_price()));
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

    private void validateDistrictData(PriceDTO district) {
        PriceDTO location = priceRepository.findPriceLocation(district.getDistrict_name());
        if (Objects.isNull(location))
            throw new DistrictBadRequestException(Message.DISTRICT_NOT_EXISTS);
        if (location.getDistrict_price() != district.getDistrict_price())
            throw new DistrictBadRequestException(Message.DISTRICT_WRONG_PRICE);
    }

    private double calculatePrice(double result, double price) {
        return result * price;
    }
}

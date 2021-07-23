package com.example.desafiotesting.services;

import com.example.desafiotesting.dto.DistrictDTO;
import com.example.desafiotesting.dto.EnvironmentDTO;
import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.dto.response.PropertyResponseDTO;
import com.example.desafiotesting.exceptions.DistrictException;
import com.example.desafiotesting.exceptions.FileException;
import org.springframework.stereotype.Service;

@Service
public class CalculateService implements ICalculateService {

    IDistrictService districtService;

    public CalculateService(IDistrictService districtService) {
        this.districtService = districtService;
    }

    public PropertyResponseDTO calculate(PropertyDTO property) throws DistrictException, FileException {
        DistrictDTO district = this.districtService.getDistrictByName(property.getDistrict().getDistrict_name());
        if (!district.getDistrict_price().equals(property.getDistrict().getDistrict_price()))
            throw new DistrictException(DistrictException.PRICE_NOT_EQUALS);
        PropertyResponseDTO response = new PropertyResponseDTO(property);
        calculateRoomSquareFeet(property, response);
        response.setPrice(calculatePrice(response.getSquareMetter(), property.getDistrict().getDistrict_price()));
        return response;
    }

    private void calculateRoomSquareFeet(PropertyDTO property, PropertyResponseDTO response) {
        Double totalSquareMetter = 0.0;
        EnvironmentDTO biggest = null;
        Double maxRoom = 0.0;
        for (EnvironmentDTO environment : property.getEnvironments()) {
            double squareMetter = environment.getSquareMetter();
            totalSquareMetter += squareMetter;
            if (biggest == null || squareMetter > maxRoom) {
                biggest = environment;
                maxRoom = squareMetter;
            }
        }
        response.setSquareMetter(totalSquareMetter);
        response.setBiggest(biggest);
    }

    private Double calculatePrice(Double result, Double price) {
        return result * price;
    }
}

package com.mercadolibre.tucasitaTasaciones.service;

import com.mercadolibre.tucasitaTasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitaTasaciones.dto.EnvironmentDTO;
import com.mercadolibre.tucasitaTasaciones.dto.PropertyDTO;
import com.mercadolibre.tucasitaTasaciones.dto.response.EnvironmentResponseDTO;
import com.mercadolibre.tucasitaTasaciones.dto.response.PropertyResponseDTO;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationNotFound;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationPriceIncorrect;
import com.mercadolibre.tucasitaTasaciones.repositories.CalculateRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculateService {

    CalculateRepository calculateRepository;

    public CalculateServiceImpl(CalculateRepository calculateRepository) {
        this.calculateRepository = calculateRepository;
    }

    /**
     *
     * @param house
     * @return PropertyResponseDTO
     * @throws ExceptionLocationNotFound
     * @throws ExceptionLocationPriceIncorrect
     */
    public PropertyResponseDTO calculate(PropertyDTO house) throws ExceptionLocationNotFound, ExceptionLocationPriceIncorrect {
        DistrictDTO districtDTO = calculateRepository.findPriceLocation(house.getDistrict().getDistrict_name());
        calculateRepository.checkPrice(house.getDistrict());
        PropertyResponseDTO response = new PropertyResponseDTO();
        response.setDistrict(house.getDistrict());
        response.setProp_name(house.getProp_name());
        calculateRoomSquareFeet(house, response);
        response.setPrice(calculatePrice(response.getSquareFeet(), districtDTO.getDistrict_price()));
        return response;
    }

    /**
     * Calcula los metros cuadrados de cada habitación, la habitación más grande
     * y los metros cuadrados totales de la propiedad
     * @param house
     * @param response
     */
    private void calculateRoomSquareFeet(PropertyDTO house, PropertyResponseDTO response) {
        Double totalSquareFeet = 0.0;
        EnvironmentDTO biggest = null;
        Double maxRoom = 0.0;
        for (EnvironmentDTO room : house.getEnvironments()) {
            Double squareFeet = room.getSquareFeet();
            EnvironmentResponseDTO environmentResponseDTO = new EnvironmentResponseDTO(room.getEnvironment_name(), room.getSquareFeet());
            response.getSquareFeetEnvironments().add(environmentResponseDTO);
            totalSquareFeet += squareFeet;
            if (biggest == null || squareFeet > maxRoom) {
                biggest = room;
                maxRoom = squareFeet;
            }
        }
        response.setSquareFeet(totalSquareFeet);
        response.setBiggest(biggest);
    }

    /**
     * Calcula el precio de una propiedad
     * @param result
     * @param price
     * @return double
     */
    private double calculatePrice(Double result, Double price) {
        return result * price;
    }

}

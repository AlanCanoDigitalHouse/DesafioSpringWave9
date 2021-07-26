package com.mercadolibre.calculadorametroscuadrados.services;

import com.mercadolibre.calculadorametroscuadrados.dtos.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectDistrictPriceException;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculateService {

    private DistrictRepository districtRepository;

    public CalculateServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public HouseResponseDTO calculate(HouseDTO house) throws DistrictNotFoundException, IncorrectDistrictPriceException {
        validateDistrictPrice(house.getDistrict());
        HouseResponseDTO response = new HouseResponseDTO(house);
        house.getDistrict().setDistrict_price(districtRepository.findPriceDistrict(house.getDistrict()));
        calculateRoomSquareFeet(house, response);
        response.setDistrict(house.getDistrict());
        response.setPrice(calculatePrice(house.getDistrict(), response.getSquareFeet()));
        return response;
    }

    private void calculateRoomSquareFeet(HouseDTO house, HouseResponseDTO response) {
        Double totalSquareFeet = 0.0;
        EnvironmentDTO biggest = null;
        Double maxRoom = 0.0;
        for (EnvironmentDTO room : house.getEnvironments()) {
            room.setEnvironment_square_meters(getSquareFeetFromEnvironment(room));
            Double squareFeet = room.getEnvironment_square_meters();
            totalSquareFeet += squareFeet;
            if (biggest == null || squareFeet > maxRoom) {
                biggest = room;
                maxRoom = squareFeet;
            }
        }
        response.setSquareFeet(totalSquareFeet);
        response.setBiggest(biggest);
    }

    private Double calculatePrice(DistrictDTO district, Double result) {
        return district.getDistrict_price() * result;
    }

    private void validateDistrictPrice(DistrictDTO district) throws IncorrectDistrictPriceException, DistrictNotFoundException {
        if (!district.getDistrict_price().equals(districtRepository.findPriceDistrict(district)))
            throw new IncorrectDistrictPriceException();
    }

    private Double getSquareFeetFromEnvironment(EnvironmentDTO environment) {
        Double result = 0.0;
        if (environment.getEnvironment_width() != null && environment.getEnvironment_length() != null)
            result = environment.getEnvironment_length() * environment.getEnvironment_width();
        return result;
    }


}

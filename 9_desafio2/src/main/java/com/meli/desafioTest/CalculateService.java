package com.meli.desafioTest;

import com.meli.desafioTest.Dtos.*;
import com.meli.desafioTest.exception.DistrictNotFoundException;
import com.meli.desafioTest.exception.DistrictPriceNotMatchException;
import com.meli.desafioTest.repository.IRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculateService implements ICalculateService {
    private static IRepository REPOSITORY;

    public CalculateService(IRepository repository) {
        REPOSITORY = repository;
    }

    //Valida datos del barrio y llama a los metodos que asignan
    // precio total, metros cuadrados y habitacion mas grande
    public HouseResponseDTO calculate(HouseDTO house) {
        DistrictDTO district = REPOSITORY.getDistrictByName(house.getDistrict().getDistrict_name());
        if (district == null) {
            throw new DistrictNotFoundException(house.getDistrict().getDistrict_name());
        }
        if (!district.getDistrict_price().equals(house.getDistrict().getDistrict_price())) {
            throw new DistrictPriceNotMatchException();
        }
        HouseResponseDTO response = new HouseResponseDTO(house);
        calculateRoomSquareMeters(house, response);
        response.setPrice(calculatePrice(response.getSquareMeters(), house.getDistrict().getDistrict_price()));
        return response;
    }

    //Calcula y asigna la totalidad de metros cuadrados de la casay la habitacion mas grande
    private void calculateRoomSquareMeters(HouseDTO house, HouseResponseDTO response) {
        Double totalSquareMeters = 0.0;
        EnvironmentDTO biggest = null;
        Double maxRoom = 0.0;
        for (EnvironmentDTO room : house.getEnvironments()) {
            totalSquareMeters += room.getEnvironment_squareMeters();
            if (biggest == null || room.getEnvironment_squareMeters() > maxRoom) {
                biggest = room;
                maxRoom = room.getEnvironment_squareMeters();
            }
        }
        response.setSquareMeters(totalSquareMeters);
        response.setBiggest(biggest);
    }

    //Calcula el precio de la casa en base al precio enviado y la totalidad de metros cuadrados
    private Double calculatePrice(Double meters, Double districtPrice) {
        return meters * districtPrice;
    }
}

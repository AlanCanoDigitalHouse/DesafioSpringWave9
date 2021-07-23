package com.mercadolibre.calculadorametroscuadrados.utils;

import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;

import javax.validation.Valid;
import java.util.Optional;

public class UtilsHome {
    public static void calculateRoomSquareFeet(HouseDTO house, HouseResponseDTO response) {
        Double totalSquareFeet = 0.0;
        Optional<@Valid EnvironmentDTO> biggest = house.getEnvironments().stream().findFirst();
        if(biggest.isPresent()){
            Double maxRoom = 0.0;
            for (EnvironmentDTO room : house.getEnvironments()) {
                Double squareFeet = room.getSquareFeet();
                totalSquareFeet += squareFeet;
                if ( squareFeet > maxRoom){
                    biggest = Optional.of(room);
                    maxRoom = squareFeet;
                }
            }
            response.setProp_area(totalSquareFeet);
            response.setBiggest_environment(new EnvironmentResponseDTO(biggest.get().getEnvironment_name(),biggest.get().getSquareFeet()));
        }
    }
}

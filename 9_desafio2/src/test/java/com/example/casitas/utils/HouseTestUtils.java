package com.example.casitas.utils;

import com.example.casitas.dtos.EnvironmentDTO;
import com.example.casitas.dtos.HouseDTO;

import java.util.ArrayList;
import java.util.List;

public class HouseTestUtils {

    public static HouseDTO buildHouse() {
        HouseDTO house = new HouseDTO();

        List<EnvironmentDTO> environments = new ArrayList<>();
        EnvironmentDTO cocina = new EnvironmentDTO("Cocina", 3.0, 4.0);
        EnvironmentDTO living = new EnvironmentDTO("Living", 5.0, 5.0);
        EnvironmentDTO banio = new EnvironmentDTO("Banio", 2.0, 3.0);
        EnvironmentDTO habitacion = new EnvironmentDTO("Habitacion", 3.0, 3.5);

        environments.add(cocina);
        environments.add(living);
        environments.add(banio);
        environments.add(habitacion);

        house.setPropertyName("La casita de Brian");
        house.setDistrictName("Palermo");
        house.setDistrictPrice(50.0);
        house.setEnvironments(environments);

        return house;
    }
}

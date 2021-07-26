package com.example.tucasitatasaciones.units.utils;

import com.example.tucasitatasaciones.dtos.DistrictDTO;
import com.example.tucasitatasaciones.dtos.EnviromentDTO;
import com.example.tucasitatasaciones.dtos.PropertyDTO;
import com.example.tucasitatasaciones.dtos.response.PropertyResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.List;

public class UnitTestBuilder {
    public static PropertyDTO create1Property2Enviroments() {
        EnviromentDTO enviroment1 = new EnviromentDTO("Banio", 10.0, 10.0, null);
        EnviromentDTO enviroment2 = new EnviromentDTO("Pieza", 20.0, 25.0, null);
        List<EnviromentDTO> enviroments = new ArrayList<>();
        enviroments.add(enviroment1);
        enviroments.add(enviroment2);

        return PropertyDTO.builder()
                .prop_name("Santa")
                .district(new DistrictDTO("Madero", 75.0))
                .enviroments(enviroments)
                .build();
    }

    public static PropertyResponseDTO property1Response2Env() {
        EnviromentDTO enviroment1 = new EnviromentDTO("Banio", 10.0, 10.0, 100.0);
        EnviromentDTO enviroment2 = new EnviromentDTO("Pieza", 20.0, 25.0, 500.0);
        List<EnviromentDTO> enviroments = new ArrayList<>();
        enviroments.add(enviroment1);
        enviroments.add(enviroment2);
        return PropertyResponseDTO.builder()
                .totalMts2(600.0)
                .totalPrice(45000.0)
                .biggestEnviroment(enviroment2)
                .enviroments(enviroments)
                .build();
    }

    public static <T> String writeObjectToString (T property) throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        return writer.writeValueAsString(property);
    }
}

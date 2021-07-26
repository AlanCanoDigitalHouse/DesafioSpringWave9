package com.kjcelis.calculadora_mts.util;

import com.kjcelis.calculadora_mts.dto.DistrictDTO;
import com.kjcelis.calculadora_mts.dto.request.EnvironmentDTO;
import com.kjcelis.calculadora_mts.dto.request.HouseRequestDTO;
import com.kjcelis.calculadora_mts.dto.response.HouseResponseDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtilGenerator {
    public static List<EnvironmentDTO> getEvironmentSet() {
        List<EnvironmentDTO> enviroments = new ArrayList<>();
        EnvironmentDTO environmentDTO = new EnvironmentDTO("Cocina", 23, 21);
        EnvironmentDTO environmentDTO1 = new EnvironmentDTO("Sala", 10, 30);
        enviroments.add(environmentDTO);
        enviroments.add(environmentDTO1);
        return enviroments;
    }

    public static Map<String, Double> squareMetersEnvsSet() {
        Map<String, Double> squareMetersEnvs = new HashMap<>();
        squareMetersEnvs.put("Cocina", 483.0);
        squareMetersEnvs.put("Sala", 300.0);
        return squareMetersEnvs;
    }


    public static HouseRequestDTO getRequestDTO() {
        return new HouseRequestDTO("Casita", "Bosa", 500, TestUtilGenerator.getEvironmentSet());
    }

    public static HouseResponseDTO getResponseDTO() {

        return new HouseResponseDTO("Casita", new DistrictDTO("Bosa", 500), 783, 391500.0, "Cocina", TestUtilGenerator.squareMetersEnvsSet());
    }


}

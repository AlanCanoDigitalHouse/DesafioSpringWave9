package com.tucasitatasaciones.utils;

import com.tucasitatasaciones.DTOs.EnvironmentDTO;
import com.tucasitatasaciones.DTOs.PriceDTO;
import com.tucasitatasaciones.DTOs.PropertyDTO;
import com.tucasitatasaciones.DTOs.PropertyResponseDTO;

import java.util.ArrayList;
import java.util.List;

// Los test son escritos de forma constante para no utilizar codigo dinamico que podria estar usando la misma
// logica que el metodo a testear, lo que no seria buena practica testear una logica usando la misma logica
// para generar esos mismo casos de prueba
public class TestGenerator {

    public static PropertyDTO getRequestPropertyDTO() {
        return new PropertyDTO("Name Of Property", getThreeEnvironments(), new PriceDTO("Recoleta", 900.0));
    }

    public static PropertyResponseDTO getResponsePropertyDTO() {
        PropertyResponseDTO response = new PropertyResponseDTO(getRequestPropertyDTO());
        response.setSquareFeet(50 + 200 + 25);
        response.setBiggest(getThreeEnvironments().get(1));
        response.setPrice(247500);//50 * 900 + 200 * 900 + 25 * 900
        return response;
    }

    private static List<EnvironmentDTO> getThreeEnvironments() {
        EnvironmentDTO env1 = new EnvironmentDTO("Cocina", 10.0, 5.0);   // inx:0 - 50m^2
        EnvironmentDTO env2 = new EnvironmentDTO("Comedor", 20.0, 10.0); // inx:1 - 200m^2 BIGGEST
        EnvironmentDTO env3 = new EnvironmentDTO("Habitacion", 5.0, 5.0);// inx:2 - 25m^2
        List<EnvironmentDTO> environmentDTOS = new ArrayList<>();
        environmentDTOS.add(env1);
        environmentDTOS.add(env2);
        environmentDTOS.add(env3);
        return environmentDTOS;
    }

    public static double[] getSquaresEnvironments() {
        double[] test = new double[3];
        test[0] = 50;   // 10 * 5.0
        test[1] = 200;  // 20 * 10
        test[2] = 25;   // 5 * 5
        return test;
    }

    public static PropertyDTO getRequestNotExistPropertyDTO() {
        return new PropertyDTO("Name Of Property", getThreeEnvironments(), new PriceDTO("N/A", 900.0));
    }

    public static PropertyDTO getRequestWrongPricePropertyDTO() {
        return new PropertyDTO("This Is A Property", getThreeEnvironments(), new PriceDTO("Palermo", 10));
    }
}

package com.example.tucasitatasaciones.handlers;

import com.example.tucasitatasaciones.dtos.EnviromentDTO;
import com.example.tucasitatasaciones.dtos.PropertyDTO;

import java.util.Comparator;
import java.util.List;

public class ServiceHandlers {
    public static Double calculateMts2FromProperty(PropertyDTO property) {
        return property.getEnviroments().stream().reduce(0D, (total, e) -> total + e.getEnviroment_mts2(), Double::sum);
    }

    public static Double calculatePriceOfProperty(PropertyDTO property) {
        return property.getDistrict().getDistrict_price() * calculateMts2FromProperty(property);
    }

    public static EnviromentDTO biggestEnviromentFromProperty(PropertyDTO property) {
        return property.getEnviroments().stream().max(Comparator.comparing(enviromentDTO -> enviromentDTO.getEnviroment_mts2())).orElse(null);
    }

    public static List<EnviromentDTO> calculateMt2FromEveryEnviroment(PropertyDTO property) {
        property.getEnviroments().stream().forEach(enviroment -> enviroment.setEnviroment_mts2(calculateMts2(enviroment)));
        return property.getEnviroments();
    }

    public static Double calculateMts2(EnviromentDTO enviroment) {
        return enviroment.getEnviroment_width() * enviroment.getEnviroment_length();
    }
}

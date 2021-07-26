package com.mercadolibre.tucasitaTasaciones.util;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.mercadolibre.tucasitaTasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitaTasaciones.dto.EnvironmentDTO;
import com.mercadolibre.tucasitaTasaciones.dto.PropertyDTO;
import com.mercadolibre.tucasitaTasaciones.dto.response.EnvironmentResponseDTO;
import com.mercadolibre.tucasitaTasaciones.dto.response.PropertyResponseDTO;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestUtilsGenerator {
    private static String SCOPE;
    public static void districtFile() {
        Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyDTO getPropertyWith3Environments(String name) {
        EnvironmentDTO env1 = new EnvironmentDTO("Cocina", 20.0, 20.0);
        EnvironmentDTO env2 = new EnvironmentDTO("Habitacion", 2.5, 3.0);
        EnvironmentDTO env3 = new EnvironmentDTO("Comedor", 7.0, 30.0);

        List<EnvironmentDTO> enviroments = new ArrayList<>();
        enviroments.add(env1);
        enviroments.add(env2);
        enviroments.add(env3);

        DistrictDTO district = new DistrictDTO("Palermo", 1000.0);
        PropertyDTO property = new PropertyDTO();
        property.setProp_name(name);
        property.setEnvironments(enviroments);
        property.setDistrict(district);

        return property;
    }

    public static PropertyResponseDTO getPropertyResponseWith3Environments(String name) {
        EnvironmentResponseDTO env1 = new EnvironmentResponseDTO("Cocina", 400.0);
        EnvironmentResponseDTO env2 = new EnvironmentResponseDTO("Habitacion", 7.5);
        EnvironmentResponseDTO env3 = new EnvironmentResponseDTO("Comedor", 210.0);

        EnvironmentDTO biggest = new EnvironmentDTO("Cocina", 20.0, 20.0);
        List<EnvironmentResponseDTO> enviroments = new ArrayList<>();
        enviroments.add(env1);
        enviroments.add(env2);
        enviroments.add(env3);

        DistrictDTO district = new DistrictDTO("Palermo", 1000.0);
        PropertyResponseDTO property = new PropertyResponseDTO();
        property.setProp_name(name);
        property.setSquareFeetEnvironments(enviroments);
        property.setDistrict(district);
        property.setPrice(617500.0);
        property.setSquareFeet(617.5);
        property.setBiggest(biggest);

        return property;
    }

    public static PropertyDTO getPropertyNotExist() {
        EnvironmentDTO env1 = new EnvironmentDTO("Cocina", 20.0, 20.0);
        EnvironmentDTO env2 = new EnvironmentDTO("Baño", 2.5, 3.0);
        EnvironmentDTO env3 = new EnvironmentDTO("Comedor", 7.0, 30.0);

        List<EnvironmentDTO> enviroments = new ArrayList<>();
        enviroments.add(env1);
        enviroments.add(env2);
        enviroments.add(env3);

        DistrictDTO district = new DistrictDTO("Mendoza", 1000.0);
        PropertyDTO property = new PropertyDTO();
        property.setEnvironments(enviroments);
        property.setDistrict(district);
        property.setProp_name("Casita");

        return property;
    }

    public static PropertyDTO getPropertyWrong() {
        EnvironmentDTO env1 = new EnvironmentDTO("Cocina", 20.0, 20.0);
        EnvironmentDTO env2 = new EnvironmentDTO("Baño", 2.5, 3.0);
        EnvironmentDTO env3 = new EnvironmentDTO("Comedor", 7.0, 30.0);

        List<EnvironmentDTO> enviroments = new ArrayList<>();
        enviroments.add(env1);
        enviroments.add(env2);
        enviroments.add(env3);

        DistrictDTO district = new DistrictDTO("Palermo", 1000.0);
        PropertyDTO property = new PropertyDTO();
        property.setEnvironments(enviroments);
        property.setDistrict(district);

        return property;
    }

    public static PropertyDTO getPropertyPriceWrong() {
        EnvironmentDTO env1 = new EnvironmentDTO("Cocina", 20.0, 20.0);
        EnvironmentDTO env2 = new EnvironmentDTO("Baño", 2.5, 3.0);
        EnvironmentDTO env3 = new EnvironmentDTO("Comedor", 7.0, 30.0);

        List<EnvironmentDTO> enviroments = new ArrayList<>();
        enviroments.add(env1);
        enviroments.add(env2);
        enviroments.add(env3);

        DistrictDTO district = new DistrictDTO("Palermo", 100.0);
        PropertyDTO property = new PropertyDTO();
        property.setEnvironments(enviroments);
        property.setDistrict(district);
        property.setProp_name("Casita");

        return property;
    }

    public static DistrictDTO getDistrictExist() {
        return new DistrictDTO("Palermo", 1000.0);
    }

    public static DistrictDTO getDistrictNotExist() {
        return new DistrictDTO("Mendoza", 1000.0);
    }

    public static DistrictDTO getDistrictPriceNotExist() {
        return new DistrictDTO("Palermo", 410.0);
    }

}

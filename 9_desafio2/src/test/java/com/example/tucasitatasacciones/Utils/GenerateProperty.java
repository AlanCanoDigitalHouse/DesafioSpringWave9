package com.example.tucasitatasacciones.Utils;

import com.example.tucasitatasacciones.dto.request.district.DistrictRequestDTO;
import com.example.tucasitatasacciones.dto.request.property.PropertyEnvironmentRequestDTO;
import com.example.tucasitatasacciones.dto.request.property.PropertyRequestDTO;

import java.util.ArrayList;
import java.util.List;

public class GenerateProperty {
    public static PropertyRequestDTO getValidProperty() {
        List<PropertyEnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Kitchen").environment_width(15.0).environment_length(20.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Bedroom").environment_width(15.0).environment_length(20.0).build());
        DistrictRequestDTO districtRequestDTO = DistrictRequestDTO.builder().district_name("Soacha").district_price(200.0).build();
        return PropertyRequestDTO.builder().prop_name("Soacha house").district(districtRequestDTO).environments(environments).build();
    }


    public static List<PropertyEnvironmentRequestDTO> getKitchenIsBiggerRoom() {

        // caso en el que la cocina es el cuarto mas grande
        List<PropertyEnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Kitchen").environment_width(25.0).environment_length(25.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Bedroom").environment_width(20.0).environment_length(20.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Living").environment_width(9.0).environment_length(9.0).build());
        return environments;
    }

    public static List<PropertyEnvironmentRequestDTO> getSquareMeterPerEnvironment() {
        List<PropertyEnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Kitchen").environment_width(20.0).environment_length(20.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Bedroom").environment_width(20.0).environment_length(20.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Living").environment_width(20.0).environment_length(20.0).build());
        return environments;
    }





}


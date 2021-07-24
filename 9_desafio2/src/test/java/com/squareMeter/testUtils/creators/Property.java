package com.squareMeter.testUtils.creators;

import com.squareMeter.dto.request.district.DistrictRequestDTO;
import com.squareMeter.dto.request.property.PropertyEnvironmentRequestDTO;
import com.squareMeter.dto.request.property.PropertyRequestDTO;

import java.util.ArrayList;
import java.util.List;

public class Property {
    public static PropertyRequestDTO getValidProperty() {
        List<PropertyEnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Bathroom").environment_width(20.0).environment_length(30.).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Sleep room").environment_width(15.0).environment_length(20.0).build());
        DistrictRequestDTO districtRequestDTO = DistrictRequestDTO.builder().district_name("Galicia").district_price(800.0).build();
        return PropertyRequestDTO.builder().prop_name("A big house name").district(districtRequestDTO).environments(environments).build();
    }

    public static PropertyRequestDTO getInvalidProperty() {
        List<PropertyEnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name(null).environment_width(30.0).environment_length(30.).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Sleep room").environment_width(50.0).environment_length(20.0).build());
        DistrictRequestDTO districtRequestDTO = DistrictRequestDTO.builder().district_name("Galicia").district_price(800.0).build();
        return PropertyRequestDTO.builder().prop_name("A big house name").district(districtRequestDTO).environments(environments).build();
    }

    public static PropertyRequestDTO getNullNames() {
        List<PropertyEnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_width(20.0).environment_length(30.).build());
        DistrictRequestDTO districtRequestDTO = DistrictRequestDTO.builder().district_price(800.0).build();
        return PropertyRequestDTO.builder().district(districtRequestDTO).environments(environments).build();
    }

    public static PropertyRequestDTO getBadSizes() {
        List<PropertyEnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Bathroom").environment_width(100.0).environment_length(100.0).build());
        DistrictRequestDTO districtRequestDTO = DistrictRequestDTO.builder().district_name("Galicia").district_price(800.0).build();
        return PropertyRequestDTO.builder().prop_name("A big house name").district(districtRequestDTO).environments(environments).build();
    }

    public static List<PropertyEnvironmentRequestDTO> getEnvironmentsSum300SquareMeters() {
        List<PropertyEnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Bathroom").environment_width(10.0).environment_length(10.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Room 1").environment_width(10.0).environment_length(10.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Room 2").environment_width(10.0).environment_length(10.0).build());
        return environments;
    }

    public static List<PropertyEnvironmentRequestDTO> getEnvironmentsBiggerIsBathroom() {
        List<PropertyEnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Room 1").environment_width(10.0).environment_length(10.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Bathroom").environment_width(20.0).environment_length(20.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Room 2").environment_width(10.0).environment_length(10.0).build());
        return environments;
    }
}

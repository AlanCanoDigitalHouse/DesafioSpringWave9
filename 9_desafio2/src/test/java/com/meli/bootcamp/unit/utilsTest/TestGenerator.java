package com.meli.bootcamp.unit.utilsTest;

import com.meli.bootcamp.dto.DistrictDTO;
import com.meli.bootcamp.dto.EnvironmentDTO;
import com.meli.bootcamp.dto.EnvironmentDetailsDTO;
import com.meli.bootcamp.dto.ValuationDTO;
import com.meli.bootcamp.dto.request.PropertyDTO;
import com.meli.bootcamp.model.Property;

import java.util.ArrayList;
import java.util.List;

public class TestGenerator {
    /**
     * Generate a new Property
     *
     * @return Property
     */
    public static Property PropertyTest() {
        return new Property(
                PropertyDTO.builder().prop_name("Propiedad_Test")
                        .district(new DistrictDTO("Belgrano", 1555.0))
                        .environments(generateEnvironmentDTO())
                        .build());
    }

    /**
     * Generate a propertyDTO, a payload for request
     *
     * @return PropertyDTO
     */
    public static PropertyDTO PropertyDTOTest() {

        return PropertyDTO.builder().prop_name("Propiedad_Test")
                .district(new DistrictDTO("Belgrano", 1555.0))
                .environments(generateEnvironmentDTO())
                .build();
    }

    /**
     * A PropertyDTO, with an invalid District name
     * @return PropertyDTO
     */
    public static PropertyDTO PropertyDTODistrictVoidTest() {

        return PropertyDTO.builder().prop_name("Propiedad_Test")
                .district(new DistrictDTO("Mitre", 1555.0))
                .environments(generateEnvironmentDTO())
                .build();
    }

    /**
     * A list of EnvironmentDTOs to be used on a PropertyDTO
     * @return List<EnvironmentDTO>
     */
    public static List<EnvironmentDTO> generateEnvironmentDTO() {
        List<EnvironmentDTO> result = new ArrayList<>();
        result.add(new EnvironmentDTO("Habitacion_1", 12.0, 10.0));
        result.add(new EnvironmentDTO("Habitacion_2", 7.0, 5.0));
        result.add(new EnvironmentDTO("Habitacion_biggest", 20.0, 30.0));
        result.add(new EnvironmentDTO("Habitacion_3", 10.0, 6.0));
        return result;
    }

    /**
     * The result of the valuation for a property
     * @return  ValuationDTO
     */
    public static ValuationDTO valuationDTO() {
        DistrictDTO dTest = new DistrictDTO("Belgrano", 1555.0);
        Property newProperty = PropertyTest();
        newProperty.calculateValuation(dTest.getDistrict_price());
        ValuationDTO response = new ValuationDTO(newProperty);
        response.setBiggest_environment(new EnvironmentDetailsDTO(newProperty.biggestEnvironment()));
        return response;
    }
}

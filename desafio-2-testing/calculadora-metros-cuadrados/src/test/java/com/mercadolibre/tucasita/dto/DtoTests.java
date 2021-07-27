package com.mercadolibre.tucasita.dto;

import com.mercadolibre.tucasita.utils.TestGeneratorUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DtoTests {

    @Test
    @DisplayName("Create a district name test")
    void createDistrictDTONameTest() {
        DistrictDTO district = new DistrictDTO("Almagro", 28D);

        assert (district.getDistrict_name().equals("Almagro"));
    }

    @Test
    @DisplayName("Set district name test")
    void createDistrictDTOSetNameTest() {
        DistrictDTO district = new DistrictDTO(null, 28D);
        district.setDistrict_name("Caballito");

        assert (district.getDistrict_name().equals("Caballito"));
    }

    @Test
    @DisplayName("Create a district price test")
    void createDistrictDTOPriceTest() {
        DistrictDTO district = new DistrictDTO("Almagro", 28D);

        assert (district.getDistrict_price().equals(28D));
    }

    @Test
    @DisplayName("Create an environment name test")
    void createEnvironmentDTONameTest() {
        EnvironmentDTO environment = new EnvironmentDTO("Habitacion", 5D, 5D);

        assert (environment.getEnvironment_name().equals("Habitacion"));
    }

    @Test
    @DisplayName("Create an environment name change test")
    void createEnvironmentDTOSetNameTest() {
        EnvironmentDTO environment = new EnvironmentDTO("Habitacion", 5D, 5D);
        environment.setEnvironment_name("Cocina");
        assert (environment.getEnvironment_name().equals("Cocina"));
    }

    @Test
    @DisplayName("Create an environment square feet test")
    void createEnvironmentDTOSquareFeetTest() {
        EnvironmentDTO environment = new EnvironmentDTO("Habitacion", 5D, 5D);

        assert (environment.getEnvironment_width().equals(5D));
        assert (environment.getEnvironment_length().equals(5D));

    }

    @Test
    @DisplayName("Create a property test ")
    void createPropertyDTOTest() {
        PropertyDTO propertyDTO = new PropertyDTO("Casa", null, null);
        propertyDTO.setDistrict(TestGeneratorUtils.recoleta);
        propertyDTO.setEnvironments(Arrays.asList(TestGeneratorUtils.bathroom, TestGeneratorUtils.livingroom));

        assert (propertyDTO.getProp_name().equals("Casa"));
        assert (propertyDTO.getEnvironments().get(0).equals(TestGeneratorUtils.bathroom));
        assert (propertyDTO.getDistrict().equals(TestGeneratorUtils.recoleta));
    }


}

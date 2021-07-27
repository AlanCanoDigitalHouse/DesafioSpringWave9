package com.mercadolibre.tucasita.handler;

import com.mercadolibre.tucasita.dto.PropertyDTO;
import com.mercadolibre.tucasita.dto.response.EnvironmentInfoResponseDTO;
import com.mercadolibre.tucasita.dto.response.PropertyInfoResponseDTO;
import com.mercadolibre.tucasita.utils.TestGeneratorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertyInfoHandlerTest {

    private PropertyInfoHandler handler;

    @BeforeEach
    void init() {
        handler = new PropertyInfoHandler();
    }

    @Test
    @DisplayName("Given property with 1 environment, when get environment info, match environment info list size")
    void getEnvironmentInfoSizeTest() {
        //arrange
        PropertyDTO property = TestGeneratorUtils.createPropertyWith1Environment();

        //act
        List<EnvironmentInfoResponseDTO> environmentInfoList =
                handler.getEnvironmentInfoList(property.getEnvironments());

        //assert
        assertEquals(1, environmentInfoList.size());

    }

    @Test
    @DisplayName("Given property with 1 environment, when get environment info, match name")
    void getEnvironmentInfoNameTest() {
        //arrange
        PropertyDTO property = TestGeneratorUtils.createPropertyWith1Environment();

        //act
        List<EnvironmentInfoResponseDTO> environmentInfoList =
                handler.getEnvironmentInfoList(property.getEnvironments());

        //assert
        assertEquals(TestGeneratorUtils.bedroom.getEnvironment_name(),
                environmentInfoList.get(0).getEnvironment_name());

    }

    @Test
    @DisplayName("Given property with 1 environment, when get environment info, match square feet")
    void getEnvironmentInfoSquareFeetTest() {
        //arrange
        PropertyDTO property = TestGeneratorUtils.createPropertyWith1Environment();
        Double bedroomSquareFeet =
                TestGeneratorUtils.bedroom.getEnvironment_width() * TestGeneratorUtils.bedroom.getEnvironment_length();

        //act
        List<EnvironmentInfoResponseDTO> environmentInfoList =
                handler.getEnvironmentInfoList(property.getEnvironments());

        //assert
        assertEquals(bedroomSquareFeet, environmentInfoList.get(0).getTotalSquareFeet());

    }

    @Test
    @DisplayName("Given property with 4 environments, get the bigger one")
    void getBiggerEnvironmentTest() {
        //arrange
        PropertyDTO propertyDTO = TestGeneratorUtils.createPropertyWith4Environments();

        PropertyInfoResponseDTO responseDTO = new PropertyInfoResponseDTO();
        responseDTO.setEnvironmentList(handler.getEnvironmentInfoList(propertyDTO.getEnvironments()));

        //act
        EnvironmentInfoResponseDTO bigger = handler.getBiggerEnvironment(responseDTO.getEnvironmentList());

        //assert
        assertEquals(TestGeneratorUtils.livingroom.getEnvironment_name(), bigger.getEnvironment_name());
    }

    @Test
    @DisplayName("Given property with 4 environments, calculate total square feet")
    void calculatePropertyTotalSquareFeetTest() {
        //arrange
        PropertyDTO propertyDTO = TestGeneratorUtils.createPropertyWith4Environments();
        PropertyInfoResponseDTO responseDTO = new PropertyInfoResponseDTO();

        responseDTO.setEnvironmentList(handler.getEnvironmentInfoList(propertyDTO.getEnvironments()));

        //act
        Double totalSquareFeet = handler.calculatePropertyTotalSquareFeet(responseDTO.getEnvironmentList());

        //assert
        assertEquals(TestGeneratorUtils.getSumOfAllEnvsSquareFeet(), totalSquareFeet);
    }

}

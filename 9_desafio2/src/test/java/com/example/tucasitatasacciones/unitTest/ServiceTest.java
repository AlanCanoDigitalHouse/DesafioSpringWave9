package com.example.tucasitatasacciones.unitTest;

import com.example.tucasitatasacciones.Utils.GenerateProperty;
import com.example.tucasitatasacciones.dto.request.property.PropertyEnvironmentRequestDTO;
import com.example.tucasitatasacciones.dto.request.property.PropertyRequestDTO;
import com.example.tucasitatasacciones.dto.response.environment.EnvironmentMetersResponseDTO;
import com.example.tucasitatasacciones.dto.response.environment.EnvironmentResponseDTO;
import com.example.tucasitatasacciones.dto.response.property.PropertySquareMetersResponseDTO;
import com.example.tucasitatasacciones.service.DistrictService;
import com.example.tucasitatasacciones.service.PropertyService;
import com.example.tucasitatasacciones.utils.Mapper;
import com.example.tucasitatasacciones.utils.RunAtStart;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    // Se realiza un doble o fake para excluir a DistrictService del test y
    // poder testear de forma unitaria PropertyService

    @Mock
    private DistrictService districtService;
    private PropertyService propertyService;

    // la anotacion permite ejecutar el metodo antes de cada test para
    // incializar los datos y que los resultados de los test no sean modificados por
    // la ejecución de otros
    @BeforeEach
    void RunStartCase() {
        propertyService = new PropertyService(new Mapper(), districtService);
        RunAtStart.refresh();
    }


//   1) Test Unitario Verificar que el total de metros cuadrados totales calculados por propiedad sea el correcto.

    @Test
    @DisplayName("Verificar que el total de metros cuadrados totales calculados por propiedad sea el correcto.")
    public void propertyMetersUnitTest(){
        // arrange
        PropertyRequestDTO property = GenerateProperty.getValidProperty();
        List<PropertyEnvironmentRequestDTO> environments = new ArrayList<>();

        // act
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Kitchen").environment_length(15.0).environment_width(15.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Bedroom").environment_length(15.0).environment_width(15.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Bathroom").environment_length(15.0).environment_width(15.0).build());
        property.setEnvironments(environments);

        // la propiedad tiene tres cuartos por ende 15 * 15 = 225 m2 por habitación * 3 = 675

        //assert
        PropertySquareMetersResponseDTO totalMetersCalculated = propertyService.getHouseTotalSquareMeters(property);
        Assert.assertEquals(675.0, totalMetersCalculated.getSquareMeters(),0.1);
    }


//    3) Verificar que efectivamente se devuelva el ambiente con mayor tamaño.

    @Test
    @DisplayName("Devuelve el ambiente con mayor tamaño. No existe ninguno que lo supere. ")
    public void getBiggerEnvironment() {
        //arrange
        PropertyRequestDTO property = GenerateProperty.getValidProperty();
        property.setEnvironments(GenerateProperty.getKitchenIsBiggerRoom());
        // act
        EnvironmentResponseDTO kitchenHouse = propertyService.getBiggerEnvironment(property);
        // assert
        Assertions.assertThat(kitchenHouse).extracting(EnvironmentResponseDTO::getEnvironment_name).isEqualTo("Kitchen");
    }

    // 4) Verificar que efectivamente el total de metros cuadrados por ambiente sea el correcto.
    @Test
    @DisplayName("Devuelve el cálculo correcto del total de metros cuadrados de un ambiente. ")
    public void getMetersPerEnvironment() {
        PropertyRequestDTO property = GenerateProperty.getValidProperty();
        property.setEnvironments(GenerateProperty.getSquareMeterPerEnvironment());
        List<EnvironmentMetersResponseDTO> environmentMeters = propertyService.getMetersPerEnvironment(property);
        Assertions.assertThat(environmentMeters).extracting(EnvironmentMetersResponseDTO::getSquare_meters).contains("400.0", "400.0", "400.0");
    }
}
















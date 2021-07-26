package com.example.tucasitatasacciones.unitTest;

import com.example.tucasitatasacciones.Utils.GenerateProperty;
import com.example.tucasitatasacciones.dto.request.property.PropertyEnvironmentRequestDTO;
import com.example.tucasitatasacciones.dto.request.property.PropertyRequestDTO;
import com.example.tucasitatasacciones.dto.response.property.PropertySquareMetersResponseDTO;
import com.example.tucasitatasacciones.service.DistrictService;
import com.example.tucasitatasacciones.service.PropertyService;
import com.example.tucasitatasacciones.utils.Mapper;
import com.example.tucasitatasacciones.utils.RunAtStart;
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
















}

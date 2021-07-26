package com.bootcamp.desafio2.service;

import com.bootcamp.desafio2.dtos.request.EnvironmentDto;
import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.services.implementation.DistrictService;
import com.bootcamp.desafio2.services.implementation.PropertyService;
import com.bootcamp.desafio2.util.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class PropertyServiceTest {

    @Mock
    DistrictService districtService;

    @InjectMocks
    PropertyService propertyService;

    @Test
    void calculateAreaWithDistrict() throws IOException, DistrictNotFoundException, ErrorMessage {

        //Verificar que el barrio de entrada exita
        //en el repositorio de barrios

        PropertyDto propertyDto = TestUtilGenerator.getHouseWithRooms("House 1");
        String name = "Palermo";
        Double price = 1000.0;

        when(districtService.neighborhoodExist(name, price)).thenReturn(true);

        propertyService.calculatePrice(propertyDto);

        verify(districtService, atLeastOnce()).neighborhoodExist(name, price);

    }

    //Validar que la Excepcion cuando no existe un Barrio.
    @Test
    void districtNotFoundTest () {
        PropertyDto propertyDto = TestUtilGenerator.getHouseWithRooms("House 1");
        DistrictNotFoundException thrown =
                Assertions.assertThrows(DistrictNotFoundException.class,
                        () -> propertyService.calculatePrice(propertyDto));

        Assertions.assertTrue(thrown.getMessage().contains("No existe un barrio que corresponda a los datos enviados"));
    }

    @Test
    void calculateArea() throws IOException, DistrictNotFoundException, ErrorMessage {

        //Verificar que el total de metros cuadrados totales
        //calculados por propiedad sea el correcto.
        PropertyDto propertyDto = TestUtilGenerator.getHouseWithRooms("House 1");

        when(districtService.neighborhoodExist(propertyDto.getDistrict().getDistrictName(), propertyDto.getDistrict().getDistrictPrice())).thenReturn(true);

        ResponseDto responseDto = propertyService.calculatePrice(propertyDto);

        Assertions.assertEquals(208.0, responseDto.getTotalArea());

    }

    @Test
    public void getBiggestEnviroment() throws IOException, DistrictNotFoundException, ErrorMessage {

        //Verificar que efectivamente se devuelva el
        //ambiente con mayor tamaño.

        PropertyDto propertyDto = TestUtilGenerator.getHouseWithRooms("House 1");

        when(districtService.neighborhoodExist(propertyDto.getDistrict().getDistrictName(), propertyDto.getDistrict().getDistrictPrice())).thenReturn(true);

        ResponseDto responseDto = propertyService.calculatePrice(propertyDto);

        Assertions.assertEquals("Bedroom", responseDto.getBiggerEnvironment());

    }

    @Test
    void getMetersPerRoom() throws IOException, DistrictNotFoundException, ErrorMessage {

        //Verificar que efectivamente el total de metros
        //cuadrados por ambiente sea el correcto.

        PropertyDto propertyDto = TestUtilGenerator.getHouseWithRooms("House 1");
        when(districtService.neighborhoodExist(propertyDto.getDistrict().getDistrictName(), propertyDto.getDistrict().getDistrictPrice())).thenReturn(true);

        ResponseDto responseDto = propertyService.calculatePrice(propertyDto);

        List<EnvironmentDto> environmentDtos = new ArrayList<>();
        environmentDtos.addAll(responseDto.getEnvironments());

        Optional<EnvironmentDto> environmentDto = environmentDtos.stream().filter(environment -> environment.getEnvironmentName().equals("Bedroom")).findFirst();

        Assertions.assertEquals(98D, environmentDto.get().getSquareMeters());

    }



    //Validar Excepcion cuando no se puede calcular el precio de la Propiedad
    @Test
    void errorMessageTest () throws IOException {
        PropertyDto propertyDto = TestUtilGenerator.getHouseWithException("House 1");
        when(districtService.neighborhoodExist(propertyDto.getDistrict().getDistrictName(), propertyDto.getDistrict().getDistrictPrice())).thenReturn(true);

        ErrorMessage thrown =
                Assertions.assertThrows(ErrorMessage.class,
                        () -> propertyService.calculatePrice(propertyDto));

        Assertions.assertTrue(thrown.getMessage().contains("No se logro calcular el precio de la propiedad"));
    }

}

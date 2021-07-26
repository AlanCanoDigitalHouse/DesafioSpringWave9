package com.mercadolibre.calculadorametroscuadrados.unit_test.controller;

import com.mercadolibre.calculadorametroscuadrados.controller.CalculateRestController;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DataNotFound;
import com.mercadolibre.calculadorametroscuadrados.service.ICalculateService;
import com.mercadolibre.calculadorametroscuadrados.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateControllerTests {
    @Mock
    ICalculateService service;

    @InjectMocks
    CalculateRestController controller;

    /* TODO: Test 1/2
    *   Probar controllador,
    *   verifica que la información llega al servicio */
    @Test
    public void calculateHouse() throws DataNotFound {
        // arrange
        HouseDTO house = TestUtilsGenerator.getHouseDTO("My casa");

        // act
        controller.calculate(house);

        // assert
        verify(service, atLeastOnce()).calculateHome(house);
    }

    /* TODO: Test 2/2
    *   devuelve el cálculo correcto del total de
    *   metros cuadrados de una propiedad*/
    @Test
    public void verifyAreaHouse() throws Exception{
        // arrange
        HouseDTO house = TestUtilsGenerator.getHouseDTO("My casa");
        HouseResponseDTO houseResponse = TestUtilsGenerator.getHouseResponseDTOfromHouseDTO(house);
        when(service.calculateHome(house)).thenReturn(houseResponse);

        HouseResponseDTO response = controller.calculate(house);
        verify(service, atLeastOnce()).calculateHome(house);
        Assertions.assertEquals(response.getProp_area(), houseResponse.getProp_area());
    }

}

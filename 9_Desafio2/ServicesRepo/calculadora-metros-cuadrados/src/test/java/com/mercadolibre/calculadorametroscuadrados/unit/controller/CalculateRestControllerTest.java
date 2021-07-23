package com.mercadolibre.calculadorametroscuadrados.unit.controller;

import com.mercadolibre.calculadorametroscuadrados.controller.CalculateRestController;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.NotFoundLocation;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateRestControllerTest {


    @Mock
    private CalculateService calculateService;

    @InjectMocks
    private CalculateRestController calculateRestController;


    @Test
    void verifyCalculateService() throws NotFoundLocation {
        //arrange
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor",20d,10d,null),
                new RoomDTO("Living",20d,30d,null));
        HouseDTO houseDTO = new HouseDTO("House","Palermo",400d,environments);
        //act

        HouseResponseDTO houseResponseDTO = calculateRestController.calculate(houseDTO);
        //assertion
        verify(calculateService, atLeastOnce()).calculate(houseDTO);
    }

    @Test
    void verifyException() throws NotFoundLocation {
        //arrange
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor",20d,10d,null),
                new RoomDTO("Living",20d,30d,null));
        HouseDTO houseDTO = new HouseDTO("asasasas","Palermo",400d,environments);
        //act
        HouseResponseDTO houseResponseDTO = calculateRestController.calculate(houseDTO);
        //assertion
        verify(calculateService, atLeastOnce()).calculate(houseDTO);


    }

}

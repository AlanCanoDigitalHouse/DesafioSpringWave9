package com.mercadolibre.calculadorametroscuadrados.unit;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.BadRequestException;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    PriceRepositoryImpl repo2;

    @InjectMocks
    CalculateService service;


    @Test
    void verificarMetrosCuadradosTotal()  {
        //arrage
        RoomDTO room1 = new RoomDTO("Pieza", 10,1);
        RoomDTO room2 = new RoomDTO("Pieza2", 10,1);
        List<RoomDTO> lista = new ArrayList<>();
        lista.add(room1);
        lista.add(room2);
        HouseDTO casa1 = new HouseDTO("Casa1","prueba","Santiago", lista,300);

        //act
        HouseResponseDTO current = service.calculate(casa1);

        //assert
        assertEquals(20,current.getSquareFeet());
    }

    @Test
    void verificarMayorTamano() {
        //arrage
        RoomDTO room1 = new RoomDTO("Pieza", 1000,1);
        RoomDTO room2 = new RoomDTO("Pieza2", 10,1);
        List<RoomDTO> lista = new ArrayList<>();
        lista.add(room1);
        lista.add(room2);
        HouseDTO casa2 = new HouseDTO("Casa1","prueba","Palermo", lista,1000);
        //mocks
        PriceDTO room1Mock = new PriceDTO("Palermo", 1000);
        when(repo2.findPriceLocation("Palermo")).thenReturn(room1Mock);

        //act
        HouseResponseDTO current = service.calculate(casa2);
        List<RoomDTO> todasMenosMayor = new ArrayList<>();
        for(RoomDTO room: casa2.getRooms()){
            if(!room.getName().equals(current.getBiggest().getName())){
                todasMenosMayor.add(room);
            }
        }
        boolean aux = true;
        for(RoomDTO room:todasMenosMayor){
            if(room.getSquareFeet()>current.getBiggest().getSquareFeet()){
                aux = false;
            }
        }
        //assert
        Mockito.verify(repo2, Mockito.atLeast(1)).findPriceLocation(Mockito.anyString());

        assertTrue(aux);
    }

    @Test
    void verificarMetrosCuadradosAmbiente() {
        //arrage
        RoomDTO room1 = new RoomDTO("Pieza", 5,1);
        RoomDTO room2 = new RoomDTO("Pieza2", 10,2);
        List<RoomDTO> lista = new ArrayList<>();
        lista.add(room1);
        lista.add(room2);
        HouseDTO casa1 = new HouseDTO("Casa1","prueba","Santiago", lista,300);

        //act
        HouseResponseDTO current = service.calculate(casa1);

        //assert
        assertEquals(5,current.getRooms().get(0).getSquareFeet());
        assertEquals(20,current.getRooms().get(1).getSquareFeet());
    }

    @Test
    void verificarMismoMetrosCuadrados() {
        //arrage
        RoomDTO room1 = new RoomDTO("Pieza", 1000,1);
        RoomDTO room2 = new RoomDTO("Pieza2", 10,1);
        List<RoomDTO> lista = new ArrayList<>();
        lista.add(room1);
        lista.add(room2);
        HouseDTO casa2 = new HouseDTO("Casa1","prueba","Palermo", lista,0);
        //mocks
        PriceDTO room1Mock = new PriceDTO("Palermo", 1000);
        when(repo2.findPriceLocation("Palermo")).thenReturn(room1Mock);

        //act
        Assertions.assertThrows(BadRequestException.class, () -> service.calculate(casa2));

    }


}

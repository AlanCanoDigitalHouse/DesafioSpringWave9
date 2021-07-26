package com.metroscuadrados.metroscuadrados.unit.ControllerTest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.metroscuadrados.metroscuadrados.Controller.CasaController;
import com.metroscuadrados.metroscuadrados.DTO.BarrioDTO;
import com.metroscuadrados.metroscuadrados.DTO.CasaDTO;
import com.metroscuadrados.metroscuadrados.DTO.HabitacionDTO;
import com.metroscuadrados.metroscuadrados.DTO.ResponseDTO;
import com.metroscuadrados.metroscuadrados.Excepciones.NotFoundException;
import com.metroscuadrados.metroscuadrados.Repositorios.IRepositorioBarrio;
import com.metroscuadrados.metroscuadrados.Repositorios.IRepositorioBarrioImp;
import com.metroscuadrados.metroscuadrados.Services.ICasaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    @InjectMocks
    CasaController controller;

    @Mock
    ICasaService service;

    @DisplayName("Comprobar que el controller funcione correctamente")
    @Test
    public void  metrosCuadradosControllerTest() throws NotFoundException {
        CasaDTO casa1;
        HabitacionDTO hab1 = new HabitacionDTO("C1Hab1", 100, 200);
        HabitacionDTO hab2 = new HabitacionDTO("C1Hab2", 50, 20);
        List<HabitacionDTO> listaDeHabitaciones = new ArrayList<>();
        listaDeHabitaciones.add(hab1);
        listaDeHabitaciones.add(hab2);
        casa1 = new CasaDTO("CasaTest1", "DirCasa1", "palermo", listaDeHabitaciones);
        Double doubleResponse = 0.0;
        List<Double> listaDeMetrosPorHabitacion = new ArrayList<>();
        listaDeMetrosPorHabitacion.add(20000.0);
        listaDeMetrosPorHabitacion.add(1000.0);
        // Creando Mocks
        BarrioDTO barrioMock = new BarrioDTO("palermo",50);

        // Creando Mocks
        System.out.println(casa1);
        when(service.metrosCuadrados(casa1)).thenReturn(doubleResponse);
        when(service.valorDeLaCasa(casa1)).thenReturn(doubleResponse);
        when(service.habitacionMasGrande(casa1)).thenReturn(hab1);
        when(service.listaDeMetrosCuadradosPorHabitacion(casa1)).thenReturn(listaDeMetrosPorHabitacion);


        ResponseDTO responseExpected = new ResponseDTO();

        responseExpected.setMetrosCuadrados(0.0);// OJO arreglar
        responseExpected.setValor(0.0); // Ojo arreglar
        responseExpected.setHabitacionMasGrande(hab1);
        responseExpected.setMetrosCuadradosPorHabitacion(listaDeMetrosPorHabitacion);
        ResponseEntity response = new ResponseEntity(responseExpected, HttpStatus.OK);

        ResponseEntity respuestaGenerada = controller.metrosCuadrados(casa1);

        Assertions.assertEquals(response, respuestaGenerada);

        verify(service, Mockito.atLeast(1)).metrosCuadrados(casa1);
        verify(service, Mockito.atLeast(1)).valorDeLaCasa(casa1);
        verify(service, Mockito.atLeast(1)).habitacionMasGrande(casa1);
        verify(service, Mockito.atLeast(1)).listaDeMetrosCuadradosPorHabitacion(casa1);
    }


}

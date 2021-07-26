package com.metroscuadrados.metroscuadrados.unit.RepositorioTest;


import com.metroscuadrados.metroscuadrados.DTO.BarrioDTO;
import com.metroscuadrados.metroscuadrados.DTO.CasaDTO;
import com.metroscuadrados.metroscuadrados.DTO.HabitacionDTO;
import com.metroscuadrados.metroscuadrados.DTO.ResponseDTO;
import com.metroscuadrados.metroscuadrados.Excepciones.NotFoundException;
import com.metroscuadrados.metroscuadrados.Repositorios.IRepositorioBarrio;
import com.metroscuadrados.metroscuadrados.Repositorios.IRepositorioBarrioImp;
import com.metroscuadrados.metroscuadrados.Services.CasaServiceImp;
import com.metroscuadrados.metroscuadrados.Services.ICasaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("Test del Repositorio")
@ExtendWith(MockitoExtension.class)
public class RepositorioTest {


    @InjectMocks
    IRepositorioBarrioImp repo;

    @Mock
    ICasaService service;

    @DisplayName("Verificar que el barrio de entrada exista en el repositorio de barrios.")
    @Test
    public void BuscarBarrioPorNombreTest () throws NotFoundException {
        //Barrio falso a testear
        BarrioDTO mockBarrioExistente = new BarrioDTO("palermo", 30);
        BarrioDTO mockBarrioNoExistente = new BarrioDTO("quilmes",10);

        //Respuesta del metodo verdadero
        BarrioDTO respuestaGenerada = repo.BuscarBarrioPorNombre(mockBarrioExistente.getNombre());

        //Verificamos que me devuelve lo que espero, espero que la respuesta sea del tipo BarrioDTO
        Assertions.assertEquals(mockBarrioExistente, respuestaGenerada);
    }
}

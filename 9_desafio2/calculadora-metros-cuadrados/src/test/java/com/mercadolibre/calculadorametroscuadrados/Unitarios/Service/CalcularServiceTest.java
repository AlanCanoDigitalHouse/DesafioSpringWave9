package com.mercadolibre.calculadorametroscuadrados.Unitarios.Service;


import com.mercadolibre.calculadorametroscuadrados.dto.AmbienteDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.AreaAmbienteResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.BarrioDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PropiedadDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.PropiedadRepository;
import com.mercadolibre.calculadorametroscuadrados.service.CalcularService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CalcularServiceTest {
    @Mock
    PropiedadRepository repositorio;

    @InjectMocks
    CalcularService service;

    PropiedadDTO devolverPropiedad(){
        List<AmbienteDTO> ambientes = new ArrayList<>();
        ambientes.add(new AmbienteDTO("Cocina",14.0,10.5));
        ambientes.add(new AmbienteDTO("Salon",12.0,25.0));
        ambientes.add(new AmbienteDTO("Cuarto",14.0,11.0));
        BarrioDTO barrio= new BarrioDTO("DF",1700);
        return new PropiedadDTO("Los alabes",barrio,ambientes);
    }


    @DisplayName("Calcular Metros Cuadrados")
    @Test
    void calcularMetrosCuadrados() throws NullPointerException {
        PropiedadDTO propiedad= devolverPropiedad();

        when(repositorio.getPropiedad()).thenReturn(propiedad);

        Double areaTotal=service.calcularMetrosCuadrados();

        Mockito.verify(repositorio,Mockito.atLeastOnce()).getPropiedad();

        assertEquals(601, areaTotal );
    }


    @DisplayName("Calcular Precio de Propiedad")
    @Test
    void calcularPrecioDePropiedad() throws NullPointerException {
        PropiedadDTO propiedad= devolverPropiedad();

        when(repositorio.getPropiedad()).thenReturn(propiedad);

        Double valorTotal=service.calcuarValorTotal();

        Mockito.verify(repositorio,Mockito.atLeastOnce()).getPropiedad();

        assertEquals(1021700, valorTotal );

    }

    @DisplayName("Devolver Ambiente Mas Grande")
    @Test
    void devolverAmbienteMasGrande() {
        PropiedadDTO propiedad= devolverPropiedad();

        AmbienteDTO ambienteEsperado=new AmbienteDTO("Salon",12.0,25.0);
        when(repositorio.getPropiedad()).thenReturn(propiedad);

        AmbienteDTO ambienteRetornado=service.calcularAmbienteMasGrande();

        Mockito.verify(repositorio,Mockito.atLeastOnce()).getPropiedad();

        assertEquals(ambienteEsperado, ambienteRetornado );
    }


    @DisplayName("Devolver Areas por Habitacion")
    @Test
    void devolverAreaPorHabitacion() throws NullPointerException {
        PropiedadDTO propiedad= devolverPropiedad();

        List<AreaAmbienteResponseDTO> ambientesEsperados=new ArrayList<>();
        ambientesEsperados.add(new AreaAmbienteResponseDTO("Cocina",147.0));
        ambientesEsperados.add(new AreaAmbienteResponseDTO("Salon",300.0));
        ambientesEsperados.add(new AreaAmbienteResponseDTO("Cuarto",154.0));

        when(repositorio.getPropiedad()).thenReturn(propiedad);

        List<AreaAmbienteResponseDTO> ambientesRetornados=service.listarAreas();

        Mockito.verify(repositorio,Mockito.atLeastOnce()).getPropiedad();

        assertEquals(ambientesEsperados, ambientesRetornados );
    }


    @Test
    @DisplayName("No inicializar la propiedad")
    void noSeInicializo() throws NullPointerException {
        Assertions.assertThrows(NullPointerException.class,()->service.calcuarValorTotal());
    }

}

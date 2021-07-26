package com.mercadolibre.calculadorametroscuadrados.Repository;

import com.mercadolibre.calculadorametroscuadrados.dto.AmbienteDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.BarrioDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PropiedadDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.BarrioNoEncontradoException;
import com.mercadolibre.calculadorametroscuadrados.repositories.PropiedadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PropiedadRepositoryTest {
    PropiedadRepository repositorio=new PropiedadRepository();


    @Test
    @DisplayName("Encuentra el barrio existente")
    void elBarrioExiste() throws BarrioNoEncontradoException {
        List<AmbienteDTO> ambientes = new ArrayList<>();
        ambientes.add(new AmbienteDTO("Cocina",14.0,10.5));
        ambientes.add(new AmbienteDTO("Salon",12.0,25.0));
        ambientes.add(new AmbienteDTO("Cuarto",14.0,11.0));
        BarrioDTO barrio= new BarrioDTO("New York",2000);
        PropiedadDTO propiedad= new PropiedadDTO("Los alabes",barrio,ambientes);

        BarrioDTO esperado=new BarrioDTO("New York",2000);

        BarrioDTO devuelto=repositorio.inicializar(propiedad);

        Assertions.assertEquals(esperado,devuelto);
    }


    @Test
    @DisplayName("Error por barrio inexistente en BD")
    void elBarrioNoExiste(){
        List<AmbienteDTO> ambientes = new ArrayList<>();
        ambientes.add(new AmbienteDTO("Cocina",14.0,10.5));
        ambientes.add(new AmbienteDTO("Salon",12.0,25.0));
        ambientes.add(new AmbienteDTO("Cuarto",14.0,11.0));
        BarrioDTO barrio= new BarrioDTO("Caracas",320);
        PropiedadDTO propiedad= new PropiedadDTO("Los alabes",barrio,ambientes);

        Assertions.assertThrows(BarrioNoEncontradoException.class,()->repositorio.inicializar(propiedad));

    }




}
package com.mercadolibre.calculadorametroscuadrados.Integrados;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.AmbienteDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.BarrioDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PropiedadDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculadoraIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    PropiedadDTO devolverPropiedad(){
        List<AmbienteDTO> ambientes = new ArrayList<>();
        ambientes.add(new AmbienteDTO("Cocina",14.0,10.5));
        ambientes.add(new AmbienteDTO("Salon",12.0,25.0));
        ambientes.add(new AmbienteDTO("Cuarto",14.0,11.0));
        BarrioDTO barrio= new BarrioDTO("Miami",5000);
        return new PropiedadDTO("Los alabes",barrio,ambientes);
    }

    @Test
    @DisplayName("Barrio supera al monto permitido")
    public void barrioConPrecioNoPermitido() throws Exception {
        PropiedadDTO propiedad=devolverPropiedad();

        ObjectWriter objectWriter=new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE,false).writer();

        String propiedadPayload=objectWriter.writeValueAsString(propiedad);

        MvcResult mvcResult= this.mockMvc.perform(post("/inicializarPropiedad")
                .contentType(MediaType.APPLICATION_JSON)
                .content(propiedadPayload)).andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"estatus\":400,\"mensaje\":{\"district.district_price\":\"El precio mÃ¡ximo permitido por metro cuadrado no puede superar los 4000 U$S.\"}}")).andReturn();
    }


    @Test
    @DisplayName("La propiedad no se ha inicializado")
    public void propiedadSinInicializar() throws Exception {
        PropiedadDTO propiedad=devolverPropiedad();

        ObjectWriter objectWriter=new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE,false).writer();

        String propiedadPayload=objectWriter.writeValueAsString(propiedad);

        MvcResult mvcResult= this.mockMvc.perform(get("/calcularMetrosDePropiedad"))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("La propiedad debe inicializarse antes de hacer cualquier operacion")).andReturn();
    }
}

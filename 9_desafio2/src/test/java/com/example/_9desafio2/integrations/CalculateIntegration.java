package com.example._9desafio2.integrations;
import com.example._9desafio2.dtos.request.DistrictDTO;
import com.example._9desafio2.dtos.request.EnviromentDTO;
import com.example._9desafio2.dtos.request.PropertyDTO;
import com.example._9desafio2.dtos.response.EnviromentResponseDTO;
import com.example._9desafio2.dtos.response.PropertyResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateIntegration {

    @Autowired
    private MockMvc mockMvc;

    //set up
    PropertyDTO payloadDTO=new PropertyDTO();
    List<EnviromentDTO> enviroments=new ArrayList<>();
    EnviromentDTO kitchen=new EnviromentDTO("Kitchen",24.0,33.0);
    EnviromentDTO dinner=new EnviromentDTO("Dinner",20.0,32.0);


    @Test
    @DisplayName("El distrito ingresado no se encuentra en la BD")
    public void districtNotFoundTest() throws Exception {


        payloadDTO.setDistrict(new DistrictDTO("Godoy cruz",2000.0));
        payloadDTO.setProp_name("Berta");
        enviroments.add(kitchen);
        enviroments.add(dinner);
        payloadDTO.setEnvironments(enviroments);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.description").value("El distrito ingresado no se ecnuentra en la base de datos"));


    }

    @Test
    @DisplayName("El precio del distrito ingresado no corresponde con el precio en la BD")
    public void notMatchPriceTest() throws Exception {

        payloadDTO.setDistrict(new DistrictDTO("Palermo",2000.0));
        payloadDTO.setProp_name("Berta");
        enviroments.add(kitchen);
        enviroments.add(dinner);
        payloadDTO.setEnvironments(enviroments);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.description").value("El precio encontrado no coincide con el precio ingresado"));

    }

    @Test
    @DisplayName("El nombre del ambiente empieza con minuscula")
    public void envirotmentNameWithoutUperCaseTest() throws Exception {

        payloadDTO.setDistrict(new DistrictDTO("Palermo",1000.0));
        payloadDTO.setProp_name("Berta");
        EnviromentDTO kitchen=new EnviromentDTO("kitchen",24.0,33.0);
        enviroments.add(kitchen);
        enviroments.add(dinner);
        payloadDTO.setEnvironments(enviroments);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.description").value("El nombre del ambiente debe comenzar con mayúscula."));

    }

    @Test
    @DisplayName("Verifica la excepcion MessageNotReadable")
    //this exception is thrown when the json cannot be mapped

    public void MessageNotReadableExcepcionTest() throws Exception {
        payloadDTO.setDistrict(new DistrictDTO("Palermo",1000.0));
        payloadDTO.setProp_name("Berta");
        EnviromentDTO kitchen=new EnviromentDTO("kitchen",24.0,33.0);
        enviroments.add(kitchen);
        enviroments.add(dinner);
        payloadDTO.setEnvironments(enviroments);

        this.mockMvc.perform(post("/calculate"))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("HttpMessageNotReadableException"));

    }

    @Test
    @DisplayName("Verifica que el DTO de respuesta sea el correcto")
    public void correctResponseDtoTest() throws Exception {

        // object sended in the payload
        payloadDTO.setProp_name("My house");
        payloadDTO.setDistrict(new DistrictDTO("Recoleta",900.0));
        enviroments.add(kitchen);
        enviroments.add(dinner);
        payloadDTO.setEnvironments(enviroments);

        //object to compare in the response
        PropertyResponseDTO responseDTO = new PropertyResponseDTO();
        responseDTO.setSquareMeters(1432.0);
        responseDTO.setPrice(1288800.0);
        responseDTO.setBiggest(new EnviromentDTO("Kitchen",24.0,33.0));
        List<EnviromentResponseDTO> enviromentsExpected=new ArrayList<>();
        enviromentsExpected.add(new EnviromentResponseDTO("Kitchen",792.0));
        enviromentsExpected.add(new EnviromentResponseDTO("Dinner",640.0));
        responseDTO.setEnviroments(enviromentsExpected);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString());

    }

}


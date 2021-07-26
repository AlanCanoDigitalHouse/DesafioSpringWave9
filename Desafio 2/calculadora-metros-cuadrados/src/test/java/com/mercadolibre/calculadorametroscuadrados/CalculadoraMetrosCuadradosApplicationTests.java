package com.mercadolibre.calculadorametroscuadrados;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CalculadoraMetrosCuadradosApplicationTests {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;


  @Test
  void calculateAllThreeRooms() throws Exception {

    HouseDTO houseDTO = new HouseDTO();
    RoomDTO aRoom = new RoomDTO();
    houseDTO.setRooms(new ArrayList<>());
    aRoom.setEnvironment_length(25.0);
    aRoom.setEnvironment_width(25.0);
    aRoom.setEnvironment_name("Cocina");
    houseDTO.getRooms().add(aRoom);

    RoomDTO anotherRoom = new RoomDTO();
    anotherRoom.setEnvironment_length(25.0);
    anotherRoom.setEnvironment_width(25.0);
    anotherRoom.setEnvironment_name("Cuarto");
    houseDTO.getRooms().add(anotherRoom);

    anotherRoom.setEnvironment_name("Afuera");
    houseDTO.getRooms().add(anotherRoom);


    houseDTO.setDistrict_price(1.0);
    houseDTO.setDistrict_name("Malvin");
    houseDTO.setProp_name("Hogar");

    this.mockMvc.perform(
            post("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(houseDTO)))
            .andDo(print()).andExpect(status().isOk());
  }

  @Test
  void calculateAllOneRoom() throws Exception {

    HouseDTO houseDTO = new HouseDTO();
    RoomDTO aRoom = new RoomDTO();
    houseDTO.setRooms(new ArrayList<>());
    aRoom.setEnvironment_length(25.0);
    aRoom.setEnvironment_width(25.0);
    aRoom.setEnvironment_name("Cocina");
    houseDTO.getRooms().add(aRoom);
    houseDTO.setDistrict_price(1.0);
    houseDTO.setDistrict_name("Malvin");
    houseDTO.setProp_name("Hogar");

    this.mockMvc.perform(
            post("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(houseDTO)))
            .andDo(print()).andExpect(status().isOk());
  }

  @Test
  void calculateAllBadDistrict() throws Exception {

    HouseDTO houseDTO = new HouseDTO();
    RoomDTO aRoom = new RoomDTO();
    houseDTO.setRooms(new ArrayList<>());
    aRoom.setEnvironment_length(25.0);
    aRoom.setEnvironment_width(25.0);
    aRoom.setEnvironment_name("Cocina");
    houseDTO.getRooms().add(aRoom);
    houseDTO.setDistrict_price(1.0);
    houseDTO.setDistrict_name("Barrio no existente en repo");
    houseDTO.setProp_name("Hogar");

    this.mockMvc.perform(
            post("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(houseDTO)))
            .andDo(print()).andExpect(status().isBadRequest());
  }
  @Test
  void calculateNull() throws Exception {

    HouseDTO houseDTO = null;

    this.mockMvc.perform(
            post("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(houseDTO)))
            .andDo(print()).andExpect(status().isBadRequest());
  }

}

package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.requests.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.requests.RoomRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.ResponseErrorDto;
import com.mercadolibre.calculadorametroscuadrados.dto.response.RoomResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.ErrorMessage;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CalculadoraMetrosCuadradosIntegrationTests {
  @Autowired
  private MockMvc mockMvc;

  private HouseRequestDTO houseRequestDTO;
  private static ObjectWriter writer;

  private void initializeRequest(HouseRequestDTO houseRequestDTO){
    houseRequestDTO.setName("House");
    ArrayList<RoomRequestDTO> rooms = new ArrayList<>();
    rooms.add(new RoomRequestDTO("Cocina",15,3));
    rooms.add(new RoomRequestDTO("Banio",10,2));
    houseRequestDTO.setRooms(rooms);
    houseRequestDTO.setLocation(new LocationDTO("Palermo",1000));

  }

  private void initializeResponse(HouseResponseDTO houseResponseDTO){
    houseResponseDTO.setName("House");
    houseResponseDTO.setSquareMeters((15*3 + 10*2));
    houseResponseDTO.setPrice((15*3 + 10*2)*1000);
    ArrayList<RoomResponseDTO> roomResponseDTOS = new ArrayList<>();
    RoomResponseDTO biggestRoom = new RoomResponseDTO("Cocina",15*3);
    houseResponseDTO.setBiggest(biggestRoom);
    roomResponseDTOS.add(biggestRoom);
    roomResponseDTOS.add(new RoomResponseDTO("Banio",10*2));
    houseResponseDTO.setInfoRooms(roomResponseDTOS);
  }

  @BeforeAll
  static void setWriter(){
    writer = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer();
  }

  @BeforeEach
  void setDtos(){
    houseRequestDTO = new HouseRequestDTO();
    initializeRequest(houseRequestDTO);
  }

  @Test
  @DisplayName("Validar respuesta esperada. (caso feliz)")
  public void correctDtoResponseTest() throws Exception{
    HouseResponseDTO houseResponseDTO = new HouseResponseDTO();
    initializeResponse(houseResponseDTO);

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(houseResponseDTO);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString());
  }

  //Errores de nombre de casa
  @Test
  @DisplayName("Test Validar error por nombre de casa muy largo.")
  public void errorHouseNameTooLongTest() throws Exception{
    Map<String,String> message = new HashMap<>();
    message.put("name","La longitud del nombre no puede superar los 30 caracteres.");
    ErrorMessage errorMessage =
            new ErrorMessage(400,
                    "Validations Error",
                    message);

    houseRequestDTO.setName("Houseeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString());
  }

  @Test
  @DisplayName("Test Validar por nombre de casa con minúscula")
  public void errorHouseNameWithoutCapitalLetterTest() throws Exception{
    Map<String,String> message = new HashMap<>();
    message.put("name","El nombre de la propiedad debe comenzar con mayúscula.");
    ErrorMessage errorMessage =
            new ErrorMessage(400,
                    "Validations Error",
                    message);

    houseRequestDTO.setName("house");

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
  }

  @Test
  @DisplayName("Test Validar nombre de casa vacío.")
  public void errorHouseNameEmptyTest() throws Exception{
    Map<String,String> message = new HashMap<>();
    message.put("name","El nombre de la propiedad no puede estar vacío.");
    ErrorMessage errorMessage =
            new ErrorMessage(400,
                    "Validations Error",
                    message);

    houseRequestDTO.setName("");

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
  }

  //Errores de nombre de habitacion
  @Test
  @DisplayName("Test Validar error por nombre de habitación muy largo.")
  public void errorRoomNameTooLongTest() throws Exception{
    Map<String,String> message = new HashMap<>();
    message.put("rooms[0].roomName","La longitud del nombre no puede superar los 30 caracteres.");
    ErrorMessage errorMessage =
            new ErrorMessage(400,
                    "Validations Error",
                    message);

    houseRequestDTO.getRooms().get(0).setRoomName("Bañooooooooooooooooooooooooooooooooo");

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString());
  }

  @Test
  @DisplayName("Test Validar error por nombre de habitación sin mayúscula.")
  public void errorRoomNameWithoutCapitalLetterTest() throws Exception{
    Map<String,String> message = new HashMap<>();
    message.put("rooms[0].roomName","El nombre del ambiente debe comenzar con mayúscula.");
    ErrorMessage errorMessage =
            new ErrorMessage(400,
                    "Validations Error",
                    message);

    houseRequestDTO.getRooms().get(0).setRoomName("baño");

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
  }

  @Test
  @DisplayName("Test Validar error por nombre de habitación vacío.")
  public void errorRoomNameEmptyTest() throws Exception{
    Map<String,String> message = new HashMap<>();
    message.put("rooms[0].roomName","El nombre del ambiente no puede estar vacío.");
    ErrorMessage errorMessage =
            new ErrorMessage(400,
                    "Validations Error",
                    message);

    houseRequestDTO.getRooms().get(0).setRoomName("");

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
  }

  //Dimensiones incorrectas de las habitaciones
  @Test
  @DisplayName("Test Validar habitación demasiado ancha")
  public void errorRoomWithTooBigTest() throws Exception{
    Map<String,String> message = new HashMap<>();
    message.put("rooms[0].width","El máximo ancho permitido por propiedad es de 25 mts");
    ErrorMessage errorMessage =
            new ErrorMessage(400,
                    "Validations Error",
                    message);

    houseRequestDTO.getRooms().get(0).setWidth(26);

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
  }

  @Test
  @DisplayName("Test Validar habitación demasiado angosta")
  public void errorRoomWithSmallBigTest() throws Exception{
    Map<String,String> message = new HashMap<>();
    message.put("rooms[0].width","El mínimo ancho permitido por propiedad es de 1 mt");
    ErrorMessage errorMessage =
            new ErrorMessage(400,
                    "Validations Error",
                    message);

    houseRequestDTO.getRooms().get(0).setWidth(0);

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
  }

  @Test
  @DisplayName("Test Validar habitación demasiado larga.")
  public void errorRoomLengthTooBigTest() throws Exception{
    Map<String,String> message = new HashMap<>();
    message.put("rooms[0].length","El máximo largo permitido por propiedad es de 33 mts");
    ErrorMessage errorMessage =
            new ErrorMessage(400,
                    "Validations Error",
                    message);

    houseRequestDTO.getRooms().get(0).setLength(34);

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
  }

  @Test
  @DisplayName("Test Validar habitación demasiado larga.")
  public void errorRoomLengthSmallBigTest() throws Exception{
    Map<String,String> message = new HashMap<>();
    message.put("rooms[0].length","El mínimo largo permitido por propiedad es de 1 mt");
    ErrorMessage errorMessage =
            new ErrorMessage(400,
                    "Validations Error",
                    message);

    houseRequestDTO.getRooms().get(0).setLength(0);

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
  }

  //Location errors
  @Test
  @DisplayName("Test Validar error locación inválida.")
  public void errorInvalidLocation() throws Exception{
    String message = "No se tiene información de la locación ingresada.";
    ResponseErrorDto errorMessage =
            new ResponseErrorDto(404,
                    "Not Found",
                    message);

    houseRequestDTO.getLocation().setLocation("Capital");

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
  }


  @Test
  @DisplayName("Test Validar precio de locación inválido")
  public void errorInvalidPriceLocation() throws Exception{
    String message = "El precio ingresado de la locacion no es correcto.";
    ResponseErrorDto errorMessage =
            new ResponseErrorDto(404,
                    "Not Found",
                    message);

    houseRequestDTO.getLocation().setPrice(900);

    String payloadJson = writer.writeValueAsString(houseRequestDTO);
    String responseJson = writer.writeValueAsString(errorMessage);

    MvcResult mvcResult =
            this.mockMvc.perform(get("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

    Assertions.assertEquals(responseJson , mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
  }


/*  // a) Se pide, retornar un objeto que diga la cantidad totales de metros cuadrados de la casa.
  @Test
  void calculateHouseWithOneRoom() throws Exception {
    String request = "{\"name\": \"Oficina\", \"address\": \"Monroe 800\", \"rooms\": [" +
        getRoom("Espacio abierto", 3, 3) +
        "]}";
    this.mockMvc.perform(
        post("/calculate")
        .contentType(MediaType.APPLICATION_JSON)
        .content(request))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("9")));
  }

  @Test
  void calculateHouseWithMultipleRoom() throws Exception {
    String request = "{\"name\": \"Oficina\", \"address\": \"Monroe 800\", \"rooms\": [" +
        getRoom("Espacio abierto", 5, 5) + "," +
        getRoom("Cocina", 3, 3)  + "," +
        getRoom("Baño", 2, 1) +
        "]}";
    this.mockMvc.perform(
        post("/calculate")
        .contentType(MediaType.APPLICATION_JSON)
        .content(request))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("36")));
  }

  //b) Indicar el valor de la casa tomando en cuenta que se toma como referencia USD 800 el metro cuadrado.
  @Test
  void calculateHousePrice() throws Exception {
    String request = "{\"name\": \"Oficina\", \"address\": \"Monroe 800\", \"rooms\": [" +
        getRoom("Espacio abierto", 5, 5) + "," +
        getRoom("Cocina", 3, 3)  + "," +
        getRoom("Baño", 2, 1) +
        "]}";
    this.mockMvc.perform(
        post("/calculate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("36")))
        .andExpect(jsonPath("$.price").value(28800));
  }

  //c) Retornar el objecto con la habitacion mas grande.
  @Test
  void calculateBiggestRoom() throws Exception {
    String biggestRoom = "Espacio abierto";
    String request = "{\"name\": \"Oficina\", \"address\": \"Monroe 800\", \"rooms\": [" +
        getRoom(biggestRoom, 5, 5) + "," +
        getRoom("Cocina", 3, 3)  + "," +
        getRoom("Baño", 2, 1) +
        "]}";
    this.mockMvc.perform(
        post("/calculate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.biggest.name").value(biggestRoom));
  }

  //d) Retornar la cantidad de metros cuadrados por habitación.
  @Test
  void calculateRoomsSquareFeet() throws Exception {
    String biggestRoom = "Espacio abierto";
    String request = "{\"name\": \"Oficina\", \"address\": \"Monroe 800\", \"rooms\": [" +
        getRoom(biggestRoom, 5, 5) + "," +
        getRoom("Cocina", 3, 3)  + "," +
        getRoom("Baño", 2, 1) +
        "]}";
    this.mockMvc.perform(
        post("/calculate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.rooms[0].squareFeet").value(25))
        .andExpect(jsonPath("$.rooms[1].squareFeet").value(9))
        .andExpect(jsonPath("$.rooms[2].squareFeet").value(2));
  }


  private String getRoom(String name, int width, int length) {
    return "{\"name\": \""+name+"\", \"width\": "+width+", \"length\": "+length+"}";
  }*/

}

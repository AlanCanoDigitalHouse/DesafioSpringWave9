package com.mercadolibre.calculadorametroscuadrados.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.*;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.ResponseOkDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DistrictDoesntExistException;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateServiceTest {

    @Autowired
    private CalculateService service;

    private ObjectWriter writer;

    @BeforeEach
    public void init(){
        writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
    }

    //enviando datos correctos se recibe un json con los datos esperados
    @Test
    void calculateMethodReturnAProperHouseResponse() throws DistrictDoesntExistException, JsonProcessingException {
        List<String> districtList= new ArrayList<>();
        districtList.add("demo");
        service.loadDistricts(districtList);

        DistrictDTO district = new DistrictDTO("demo", 100);
        EnviromentDTO room = new EnviromentDTO("Comedor", 10D , 10D );
        List<EnviromentDTO> rooms= new ArrayList<>();
        rooms.add(room);
        HouseDTO house = new HouseDTO("Oficina", district, rooms);

        HouseResponseDTO houseResponse = new HouseResponseDTO(house);
        houseResponse.setBiggest(room);
        houseResponse.setPrice(10000D);
        houseResponse.setSquareFeet(100D);

        String responseJSON = writer.writeValueAsString(service.calculate(house));
        String expectedJSON = writer.writeValueAsString(houseResponse);

        Assertions.assertEquals(responseJSON , expectedJSON);
    }

    //al agregar una lista con un distrito, se responde con un mensaje de OK
    @Test
    void loadOneDistrictName() throws JsonProcessingException {
        List<String> districtList= new ArrayList<>();
        districtList.add("demo");

        ResponseOkDTO response = new ResponseOkDTO(200, "Lista de distritos cargada con exito");

        Assertions.assertEquals(writer.writeValueAsString(response) , writer.writeValueAsString(service.loadDistricts(districtList)));
    }

    //al agregar una lista con multiples distritos, se responde con un mensaje de OK
    @Test
    void loadTwoDistrictNames() throws JsonProcessingException {
        List<String> districtList= new ArrayList<>();
        districtList.add("demo");
        districtList.add("demo2");

        ResponseOkDTO response = new ResponseOkDTO(200, "Lista de distritos cargada con exito");

        Assertions.assertEquals(writer.writeValueAsString(response) , writer.writeValueAsString(service.loadDistricts(districtList)));
    }
}

package com.mercadolibre.calculadorametroscuadrados;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.ResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.entities.District;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TestUtils {

    public static ObjectWriter writer = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer().withDefaultPrettyPrinter();

    public static List<District> getExampleList() throws IOException {

        File file = null;
        file = ResourceUtils.getFile("/Users/aluciano/Downloads/Bootcamp/Mis proyectos/9_Desafio2/src/test/resources/districts.json");

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<District>> typeReference = new TypeReference<List<District>>() {
        };

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<District> districts;
        districts = objectMapper.readValue(file, typeReference);

        return districts;
    }

    public static HouseDTO createInputWithInvalidPrice() {
        HouseDTO houseDTO = createValidInput();
        houseDTO.setDistrict_price(999d);
        return houseDTO;
    }

    public static HouseDTO createValidInput() {
        HouseDTO houseDTO = new HouseDTO();
        EnvironmentDTO environmentDTO = new EnvironmentDTO();
        environmentDTO.setEnvironment_name("Primero");
        environmentDTO.setEnvironment_length(2);
        environmentDTO.setEnvironment_width(4);
        EnvironmentDTO environmentDTO2 = new EnvironmentDTO();
        environmentDTO2.setEnvironment_name("Segundo");
        environmentDTO2.setEnvironment_length(2);
        environmentDTO2.setEnvironment_width(8);
        houseDTO.setProp_name("CasaTest");
        houseDTO.setDistrict_name("Campana");
        houseDTO.setEnvironments(Arrays.asList(environmentDTO, environmentDTO2));
        houseDTO.setDistrict_price(800);
        return houseDTO;
    }

    public static ResponseDTO calculateValidResponse() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setName("CasaTest");
        responseDTO.setSqm(24);
        responseDTO.setPrice(19200);
        HashMap<String, Double> environments = new HashMap<>();
        environments.put("Primero", 8d);
        environments.put("Segundo", 16d);
        responseDTO.setEnvironments(environments);
        Map.Entry<String, Double> bigger = new AbstractMap.SimpleEntry<String, Double>("Segundo", 16d);
        responseDTO.setBiggest(bigger);
        return responseDTO;
    }

    public static HouseDTO createValidationErrorInput() {
        HouseDTO houseDTO = createValidInput();
        houseDTO.setProp_name("minusculas");
        houseDTO.setDistrict_name("11234567890'1234567890123456789012345678901234567890");
        houseDTO.setDistrict_price(9999d);
        houseDTO.setEnvironments(null);
        return houseDTO;
    }

    public static Map<String, String> createFieldsValidationError() {
        Map<String, String> map = new HashMap<>();
        map.put("prop_name", "El nombre de la propiedad debe comenzar con mayúscula.");
        map.put("district_name", "La longitud del barrio no puede superar los 45 caracteres.");
        map.put("environments", "no debe estar vacío");
        map.put("district_price", "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.");
        return map;
    }

    public static String convertToJsonString(HouseDTO houseDTO) throws JsonProcessingException {
        String payloadJSON = writer.writeValueAsString(houseDTO);
        return payloadJSON;
    }

    public static String convertToJsonString(ResponseDTO responseDTO) throws JsonProcessingException {
        String payloadJSON = writer.writeValueAsString(responseDTO);
        return payloadJSON;
    }
}

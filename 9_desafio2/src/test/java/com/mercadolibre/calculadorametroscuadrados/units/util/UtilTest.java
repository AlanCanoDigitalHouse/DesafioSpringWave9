package com.mercadolibre.calculadorametroscuadrados.units.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.*;
import com.mercadolibre.calculadorametroscuadrados.models.EnvironmentModel;

import java.util.ArrayList;
import java.util.List;

public class UtilTest {

    static List<EnvironmentModel> list;
    static List<EnvironmentDTO> listEnvironments;
    static List<EnvironmentResponseDTO> listEnvironmentsResponses;
    public static List<EnvironmentModel> getData() {
        list = new ArrayList<>();
        list.add(EnvironmentModel.builder().environment_name("Balvanera").build());
        list.add(EnvironmentModel.builder().environment_name("Barracas").build());
        list.add(EnvironmentModel.builder().environment_name("Belgrano").build());
        list.add(EnvironmentModel.builder().environment_name("Boedo").build());
        list.add(EnvironmentModel.builder().environment_name("Caballito").build());
        return list;
    }

    public static List<EnvironmentDTO> loadValidEnvironments(){
        listEnvironments = new ArrayList<>();
        listEnvironments.add(EnvironmentDTO.builder().environment_name("Hab1").environment_width(12.0).environment_length(12.0).build());
        listEnvironments.add(EnvironmentDTO.builder().environment_name("Hab2").environment_width(11.0).environment_length(7.0).build());
        listEnvironments.add(EnvironmentDTO.builder().environment_name("Hab3").environment_width(10.0).environment_length(6.0).build());
        return listEnvironments;
    }

    public static List<EnvironmentResponseDTO> loadValidEnvironmentsResponse(){
        listEnvironmentsResponses = new ArrayList<>();
        listEnvironmentsResponses.add(EnvironmentResponseDTO.builder().environment_name("Hab1").environment_width(12.0).environment_length(12.0).squareFeet(144.0).build());
        listEnvironmentsResponses.add(EnvironmentResponseDTO.builder().environment_name("Hab2").environment_width(11.0).environment_length(7.0).squareFeet(77.0).build());
        listEnvironmentsResponses.add(EnvironmentResponseDTO.builder().environment_name("Hab3").environment_width(10.0).environment_length(6.0).squareFeet(60.0).build());
        return listEnvironmentsResponses;
    }

    public static EnvironmentResponseDTO getBiggest(){
        return EnvironmentResponseDTO.builder().environment_name("Hab1").environment_width(12.0).environment_length(12.0).squareFeet(144.0).build();
    }

    public static HouseDTO loadHouseDTO(String prop_name,String district_name){
        return HouseDTO.builder().prop_name(prop_name).district(DistrictDTO.builder().district_name(district_name).district_price(400.0).build()).environments(loadValidEnvironments()).build();
    }

    public static HouseResponseDTO loadHouseResponseDTO(HouseDTO houseDTO){
        HouseResponseDTO expected = new HouseResponseDTO(houseDTO);
        expected.setEnvironments(UtilTest.loadValidEnvironmentsResponse());
        expected.setSquareFeet(281.0);
        expected.setPrice(112400.0);
        expected.setBiggest(getBiggest());
        return expected;
    }


    public static String writeValueAsString(Object dto) throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        return writer.writeValueAsString(dto);
    }

    public static String writeValueAsStringWhitoutPretty(Object dto) throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        return writer.writeValueAsString(dto);
    }
    // response incomplete for validations
    public static HouseDTO loadBadHouseDTO(String prop_name, DistrictDTO districtDTO, List<EnvironmentDTO> list){
        return HouseDTO.builder().prop_name(prop_name).district(districtDTO).environments(list).build();
    }

    public static EnvironmentDTO loadBadEnvironmentDTO(String environment_name, Double environment_width, Double environment_length) {
        return EnvironmentDTO.builder().environment_name(environment_name).environment_width(environment_width).environment_length(environment_length).build();
    }

    public static DistrictDTO loadBadDistrictDTO(String district_name, Double district_price) {
        return DistrictDTO.builder().district_name(district_name).district_price(district_price).build();
    }


    public static String getValidation(String parameter, String menssage){
        return "\""+parameter+"\":\""+menssage+"\"";

    }









}

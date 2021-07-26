package com.bootcamp.desafio2.utils;

import com.bootcamp.desafio2.dtos.request.DistrictDto;
import com.bootcamp.desafio2.dtos.request.EnvironmentDto;
import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestUtilsGenerator {

    private static String SCOPE;

    public static void init() {
        Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Default
    public static PropertyDto getDefaultPropertyRequest() {
        EnvironmentDto env1 = new EnvironmentDto("Cocina",3.0,4.0,12.0);
        EnvironmentDto env2 = new EnvironmentDto("Baño",2.0,2.0,4.0);
        EnvironmentDto env3 = new EnvironmentDto("Dormitorio",3.0,3.0,9.0);
        DistrictDto dis = new DistrictDto("Belgrano",1100D);


        List<EnvironmentDto> environments = new ArrayList<>();
        environments.add(env1);
        environments.add(env2);
        environments.add(env3);

        PropertyDto property = new PropertyDto();
        property.setProp_name("Casa de Belgrano");
        property.setDistrict(dis);
        property.setEnvironments(environments);

        return property;
    }

    public static ResponseDto getDefaultPropertyResponse() {
        EnvironmentDto env1 = new EnvironmentDto("Cocina",3.0,4.0,12.0);
        EnvironmentDto env2 = new EnvironmentDto("Baño",2.0,2.0,4.0);
        EnvironmentDto env3 = new EnvironmentDto("Dormitorio",3.0,3.0,9.0);
        DistrictDto dis = new DistrictDto("Belgrano",1100D);


        List<EnvironmentDto> environments = new ArrayList<>();
        environments.add(env1);
        environments.add(env2);
        environments.add(env3);

        ResponseDto response = new ResponseDto(25.0,27500.0,"Cocina",environments);
        return response;
    }

    //Bad
    public static PropertyDto getBadPropertyRequest() {
        EnvironmentDto env1 = new EnvironmentDto("Cocina",3.0,4.0,12.0);
        EnvironmentDto env2 = new EnvironmentDto("Baño",2.0,2.0,4.0);
        DistrictDto dis = new DistrictDto("Villa Bosch",50D);


        List<EnvironmentDto> environments = new ArrayList<>();
        environments.add(env1);
        environments.add(env2);

        PropertyDto property = new PropertyDto();
        property.setProp_name("Depto de Villa Bosch");
        property.setDistrict(dis);
        property.setEnvironments(environments);

        return property;
    }

    public static ResponseDto getBadPropertyResponse() {
        EnvironmentDto env1 = new EnvironmentDto("Cocina",3.0,4.0,12.0);
        EnvironmentDto env2 = new EnvironmentDto("Baño",2.0,2.0,4.0);
        DistrictDto dis = new DistrictDto("Villa Bosch",50D);


        List<EnvironmentDto> environments = new ArrayList<>();
        environments.add(env1);
        environments.add(env2);

        ResponseDto response = new ResponseDto(25.0,27500.0,"Cocina",environments);
        return response;
    }
}

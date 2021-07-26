package com.bootcamp.desafio2.utils;

import com.bootcamp.desafio2.dtos.request.DistrictDto;
import com.bootcamp.desafio2.dtos.request.EnvironmentDto;
import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ErrorMessageDto;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TestUtilGenerator {
    private static String SCOPE;
    private static ObjectWriter mapper;

    public static void emptyPricesFile() {
        Properties properties = new Properties();
        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/prices.json"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        writer.print("[]");
        writer.close();
    }
    public static PropertyDto getProperty(String name) {
        EnvironmentDto environment = new EnvironmentDto("Victoria",15D,20D, 0D);
        EnvironmentDto environment1 = new EnvironmentDto("Villamiriam", 18.5, 23D, 0D);
        EnvironmentDto environment2 = new EnvironmentDto("Garupal", 12D,22.5, 0D);


        List<EnvironmentDto> environments = new ArrayList<>();
        environments.add(environment);
        environments.add(environment1);
        environments.add(environment2);

        DistrictDto district = new DistrictDto("Belgrano",1100D);

        PropertyDto property = new PropertyDto();
        property.setProp_name(name);
        property.setDistrict(district);
        property.setEnvironments(environments);

        return property;
    }
    public static PropertyDto getPropDistrictEmpty(String name) {
        EnvironmentDto environment = new EnvironmentDto("Victoria",15D,20D, 0D);
        EnvironmentDto environment1 = new EnvironmentDto("Villamiriam", 18.5, 23D, 0D);
        EnvironmentDto environment2 = new EnvironmentDto("Garupal", 12D,22.5, 0D);


        List<EnvironmentDto> environments = new ArrayList<>();
        environments.add(environment);
        environments.add(environment1);
        environments.add(environment2);

        DistrictDto district = new DistrictDto("",1100D);

        PropertyDto property = new PropertyDto();
        property.setProp_name(name);
        property.setDistrict(district);
        property.setEnvironments(environments);

        return property;
    }
    public static PropertyDto getPropWrongDistrict(String name) {
        EnvironmentDto environment = new EnvironmentDto("Victoria",15D,20D, 0D);
        EnvironmentDto environment1 = new EnvironmentDto("Villamiriam", 18.5, 23D, 0D);
        EnvironmentDto environment2 = new EnvironmentDto("Garupal", 12D,22.5, 0D);


        List<EnvironmentDto> environments = new ArrayList<>();
        environments.add(environment);
        environments.add(environment1);
        environments.add(environment2);

        DistrictDto district = new DistrictDto("Castillo",1100D);

        PropertyDto property = new PropertyDto();
        property.setProp_name(name);
        property.setDistrict(district);
        property.setEnvironments(environments);

        return property;
    }
    public static PropertyDto getExceededLength(String name) {
        EnvironmentDto environment = new EnvironmentDto("Victoria",35D,20D, 0D);
        EnvironmentDto environment1 = new EnvironmentDto("Villamiriam", 18.5, 23D, 0D);
        EnvironmentDto environment2 = new EnvironmentDto("Garupal", 12D,22.5, 0D);


        List<EnvironmentDto> environments = new ArrayList<>();
        environments.add(environment);
        environments.add(environment1);
        environments.add(environment2);

        DistrictDto district = new DistrictDto("Belgrano",1100D);

        PropertyDto property = new PropertyDto();
        property.setProp_name(name);
        property.setDistrict(district);
        property.setEnvironments(environments);

        return property;
    }
    public static PropertyDto getEmptyEnvironment(String name) {

        List<EnvironmentDto> environments = new ArrayList<>();

        DistrictDto district = new DistrictDto("Belgrano",1100D);

        PropertyDto property = new PropertyDto();
        property.setProp_name(name);
        property.setDistrict(district);
        property.setEnvironments(environments);

        return property;
    }
    public static List<Double> getM2(String name){
        List<Double> m2 = new ArrayList<>();
        if (name.equals("Campo"))
            m2.add(300D);
            m2.add(425.5);
            m2.add(270D);
        return m2;
    }
    public static ErrorMessageDto get400(){
        Map<String, String> fields = new HashMap<>();
        fields.put("prop_name", "El nombre de la propiedad no puede estar vacío");
        return new ErrorMessageDto(400,"Validations error", fields);
    }
    public static ErrorMessageDto get400District(){
        Map<String, String> fields = new HashMap<>();
        fields.put("district.district_name", "El barrio no puede estar vacío");
        return new ErrorMessageDto(400,"Validations error", fields);
    }
    public static ErrorMessageDto get400Capitalize(){
        Map<String, String> fields = new HashMap<>();
        fields.put("prop_name", "El nombre de la propiedad debe comenzar con mayúscula.");
        return new ErrorMessageDto(400,"Validations error", fields);
    }
    public static ErrorMessageDto get400size(){
        Map<String, String> fields = new HashMap<>();
        fields.put("prop_name", "La longitud del nombre no puede superar los 30 caracteres");
        return new ErrorMessageDto(400,"Validations error", fields);
    }
    public static ErrorMessageDto get400EmptyEnvironment(){
        Map<String, String> fields = new HashMap<>();
        fields.put("environments", "La propiedad debe tener al menos un ambiente");
        return new ErrorMessageDto(400,"Validations error", fields);
    }
    public static ErrorMessageDto get400DistrictNotFound(){
        Map<String, String> fields = new HashMap<>();
        fields.put("district", "No existe un barrio que corresponda a los datos enviados");
        return new ErrorMessageDto(400,"District error", fields);
    }
    public static ErrorMessageDto get400Width(){
        Map<String, String> fields = new HashMap<>();
        fields.put("environments[0].environment_width", "El máximo ancho permitido por propiedad es de 25mts");
        return new ErrorMessageDto(400,"Validations error", fields);
    }


    /*public static List<District> getDistrictSet() {
        District dist1 = new District("Palermo",1000D);
        District dist2 = new District("Castilla",2000D);
        District dist3 = new District("Victoria",1530D);
        District dist4 = new District("Villamiriam",2500D);

        return new ArrayList<>(){{add(dist1); add(dist2); add(dist3); add(dist4);}};
    }*/
}

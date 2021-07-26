package com.mercadolibre.squaremeter.unit.utils;

import com.mercadolibre.squaremeter.dtos.request.District;
import com.mercadolibre.squaremeter.dtos.request.Environment;
import com.mercadolibre.squaremeter.dtos.request.Home;
import com.mercadolibre.squaremeter.dtos.response.InfoEnvironments;
import com.mercadolibre.squaremeter.dtos.response.InfoHome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtilsGenerator {
    public static void initDataBase() {
    }

    public static Home getRequestNotOk() {
        District dis = new District("Sarasa", 1700.);
        List<Environment> list = new ArrayList<>();
        list.add(new Environment("Bathroom", 3.5, 3.));
        list.add(new Environment("Kitchen", 8., 2.75));
        list.add(new Environment("Room", 6., 4.));
        list.add(new Environment("Living", 6., 9.));
        return new Home("Casita 1", dis, list);
    }

    public static Home getRequestNullsAttributes() {
        return new Home(null, null, null);
    }


    public static InfoHome getResultOkReport() {
        List<InfoEnvironments> environmentsList = new ArrayList<>();
        environmentsList.add(new InfoEnvironments("Bathroom", 10.5));
        environmentsList.add(new InfoEnvironments("Kitchen", 22.));
        environmentsList.add(new InfoEnvironments("Room", 24.));
        environmentsList.add(new InfoEnvironments("Living", 54.));
        return new InfoHome(110.5, 187850., "Living", environmentsList);
    }


    public static Home getRequestOk() {
        District dis = new District("Palermo", 1700.);
        List<Environment> list = new ArrayList<>();
        list.add(new Environment("Bathroom", 3.5, 3.));
        list.add(new Environment("Kitchen", 8., 2.75));
        list.add(new Environment("Room", 6., 4.));
        list.add(new Environment("Living", 6., 9.));
        return new Home("Casita 1", dis, list);

    }

    public static Home getRequestNotValidStrings() {
        District dis = new District("palermo", 300.);
        List<Environment> list = new ArrayList<>();
        list.add(new Environment("bathroom", 3.5, 3.));
        return new Home("casita 1", dis, list);
    }

    public static Home getRequestNotValidStringsLargeAndAttributesEnvironments() {
        District dis = new District("Palermo old quarter under heavy rain - Palermo old quarter under heavy rain", 300.);
        List<Environment> list = new ArrayList<>();
        list.add(new Environment());
        list.add(new Environment("Pink kitchen with high ceilings and red lights", 8., 2.75));
        return new Home("Old house with own garage - old house with own garage", dis, list);
    }

    public static Home getRequestNotValidDouble() {
        District dis = new District("Palermo", 4500.);
        List<Environment> list = new ArrayList<>();
        list.add(new Environment("Bathroom", 25.1, 33.1));
        return new Home("Casita 1", dis, list);
    }

    public static Map<String, String> getMessageValidErrorString() {
        Map<String, String> message = new HashMap<>();
        message.put("district.district_name", "El nombre del barrio debe comenzar con mayúscula.");
        message.put("environment[0].environment_name", "El nombre del ambiente debe comenzar con mayúscula.");
        message.put("name", "El nombre de la propiedad debe comenzar con mayúscula");
        return message;
    }

    public static Map<String, String> getMessageValidErrorDouble() {
        Map<String, String> message = new HashMap<>();
        message.put("district.district_price", "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S");
        message.put("environment[0].environment_length", "El máximo largo permitido por propiedad es de 33 mt");
        message.put("environment[0].environment_width", "El máximo ancho permitido por propiedad es de 25 mts");
        return message;
    }

    public static Object getMessageValidErrorLargeStringAndAttributesEnvironments() {
        Map<String, String> message = new HashMap<>();
        message.put("environment[0].environment_name", "El nombre del ambiente no puede estar vacío.");
        message.put("environment[0].environment_width", "El ancho del ambiente no puede estar vacío.");
        message.put("environment[0].environment_length", "El largo del ambiente no puede estar vacío.");
        message.put("environment[1].environment_name", "La longitud del nombre no puede superar los 30 caracteres.");
        message.put("name", "La longitud del nombre de la propiedad no puede superar los 30 caracteres.");
        message.put("district.district_name", "La longitud del barrio no puede superar los 45 caracteres.");
        return message;

    }

    public static Object getMessageValidErrorNotNulls() {
        Map<String, String> message = new HashMap<>();
        message.put("district", "El barrio es requerido");
        message.put("name", "El nombre de la propiedad no puede estar vacío.");
        message.put("environment", "La propiedad debe contar con al menos una habitacion");
        return message;

    }
}
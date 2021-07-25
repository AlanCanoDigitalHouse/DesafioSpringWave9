package com.mercadolibre.desafio_testing.util;

public class ConstantsUtils {
    // Endpoints
    public static final String PROPERTY_ENDPOINT = "/property";
    public static final String SQR_MTS_ENDPOINT = "/sqrmts";
    public static final String DISTRICT_ENDPOINT = "/district";
    public static final String PRICE_ENDPOINT = "/price";
    public static final String BIG_ROOM_ENDPOINT = "/bigroom";
    public static final String ROOMS_ENDPOINT = "/room";

    // Routes
    public static final String PROPERTY_JSONS_ROUTE =
            "classpath:JSONExamples/property/";
    public static final String ROOMS_JSONS_ROUTE =
            "classpath:JSONExamples/environment/";
    public static final String DISTRICT_JSONS_ROUTE =
            "classpath:JSONExamples/district/";

    // Normal response messages
    public static final String PROPERTY_CREATED = "Property was created.";
    public static final String DISTRICT_CREATED = "District was created.";

    // Error messages
    public static final String INVALID_PROPERTY_NAME =
            "El nombre de la propiedad no puede estar vacío.";
    public static final String INVALID_PROPERTY_CAPITALIZATION =
            "El nombre de la propiedad debe comenzar con mayúscula.";
    public static final String PROPERTY_NAME_TOO_LONG =
            "La longitud del nombre no puede superar los 30 caracteres.";
    public static final String PROPERTY_NAME_WITH_SPACES = "Spaces are not " +
            "allowed in the property name";
    public static final String INVALID_DISTRICT_NAME =
            "El barrio no puede estar vacío.";
    public static final String DISTRICT_NAME_TOO_LONG =
            "La longitud del barrio no puede superar los 45 caracteres.";
    public static final String INVALID_PRICE =
            "El precio de un barrio no puede estar vacío";
    public static final String PRICE_TOO_BIG =
            "El precio máximo permitido por metro cuadrado" +
                    " no puede superar los 4000 U$S.";
    public static final String INVALID_ENVIRONMENT_NAME =
            "El nombre del ambiente no puede estar vacío.";
    public static final String ENVIRONMENT_NAME_NOT_CAPITALIZED =
            "El nombre del ambiente debe comenzar con mayúscula.";
    public static final String ENVIRONMENT_NAME_TOO_LONG =
            "La longitud del nombre no puede superar los 30 caracteres.";
    public static final String INVALID_ENVIRONMENT_WIDTH =
            "El ancho del ambiente no puede estar vacío.";
    public static final String ENVIRONMENT_WIDTH_TOO_BIG =
            "El máximo ancho permitido por propiedad es de 25 mts.";
    public static final String INVALID_ENVIRONMENT_LENGTH =
            "El largo del ambiente no puede estar vacío.";
    public static final String ENVIRONMENT_LENGTH_TOO_BIG =
            "El máximo largo permitido por propiedad es de 33 mts.";
    public static final String UNEXISTING_PROPERTY =
            "Property does not exists.";
    public static final String PROPERTY_ALREADY_EXISTS =
            "Property with that name already exists.";
    public static final String UNEXISTING_DISTRICT =
            "District with that name does not exists";
    public static final String DISTRICT_ALREADY_EXISTS =
            "District already exists.";
    public static final String ROOM_ALREADY_EXISTS =
            "A room with that name in that property already exists.";
}

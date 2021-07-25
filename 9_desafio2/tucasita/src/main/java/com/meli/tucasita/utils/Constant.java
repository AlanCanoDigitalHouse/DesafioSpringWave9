package com.meli.tucasita.utils;

public class Constant {

    /*MENSAJES DE ERROR*/
    public static final String ERROR_CARGA_BD = "No se pudo cargar la base de datos";
    public static final String ERROR_PRECIO_DISTRITO = "EL precio del distrito en base de datos no coincide con el ingresado por el usuario";
    public static final String ERROR_NO_DISTRITO =  "No se encontro el distrito: ";
    public static final String METHOD_ARGUMENTNOTVALID_EXCEPTION="MethodArgumentNotValidException";

    public static final String CASA_VACIA = "El nombre de la propiedad no puede estar vacío.";
    public static final String CASA_LONGITUD = "La longitud de la propiedad no puede superar los 30 caracteres.";
    public static final String CASA_FORMAT_MAYUS = "El nombre de la propiedad debe comenzar con mayúscula.";

    public static final String DISTRITO_LONGITUD = "El nombre del distrito no puede superar los 45 caracteres.";
    public static final String DISTRITO_VACIO = "El barrio no puede estar vacío.";

    public static final String PRECIO_VACIO = "El precio de un barrio no puede estar vacío.";
    public static final String PRECIO_MAXIMO = "El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S.";
    public static final String PRECIO_MINIMO = "El precio maximo permitido por metro cuadrado no puede ser menor a 1 U$S.";

    public static final String HABITACIONES_VACIAS= "LAS HABITACIONES no pueden estar vaciós.";

    public static final String HABITACION_VACIA = "El nombre deL ambiente no puede estar vacío.";
    public static final String HABITACION_LONGITUD = "La longitud del ambiente no puede superar los 30 caracteres.";
    public static final String HABITACION_FORMAT_MAYUS = "El nombre del ambiente debe comenzar con mayúscula.";
    public static final String WIDTH_VACIO = "El ancho del ambiente no puede estar vacio";
    public static final String WIDTH_MAX = "El maximo ancho permitido por propiedad es de 25 mts.";
    public static final String WIDTH_MIN = "El mininimo ancho permitido por propiedad es de 1 mts.";
    public static final String LENGTH_VACIO = "El largo del ambiente no puede estar vacio";
    public static final String LENGTH_MAX = "El maximo largo permitido por propiedad es de 33 mts.";
    public static final String LENGTH_MIN = "El mininimo largo permitido por propiedad es de 1 mts.";

}

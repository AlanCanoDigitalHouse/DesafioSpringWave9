package com.meli.socialmeli.utils;

public class Constant {

    /*url de las peticiones*/
    public static final String URIUSER = "/users";
    public static final String URIPRODUCT = "/products";
    public static final String URIFOLLOW = "/{userId}/follow/{userIdToFollow}";
    public static final String URICOUNTFOLLOWER ="{userId}/followers/count/";
    public static final String URIFOLLOWERSLIST = "/{UserID}/followers/list";
    public static final String URIFOLLOWEDLIST = "/{UserID}/followed/list";
    public static final String URINEWPOST = "/newpost";
    public static final String URIPRODUCTLIST = "/followed/{userId}/list";
    public static final String URIUNFOLLOW = "/{userId}/unfollow/{userIdToUnfollow}";


    /*mensajes al usuario*/
    public static final String OPERACION_EXITOSA = "Operacion exitosa";
    public static final String AUTOFOLLOW = "No puede seguirse a si mismo";
    public static final String USUARIO_SEGUIDO = "Ya se encuentra siguiendo al usuario con id: ";
    public static final String INFORMACION_INEXISTENTE ="No se encontro informacion con el id: ";
    public static final String INFORMACION_PRODUCTO_INEXISTENTE ="No se encontro el producto con el id: ";
    public static final String ERROR_CARGA_BD = "No se pudo cargar la base de datos";
    public static final String USUARIO_NO_SEGUIDOR = "No se encuentra siguiendo al usuario con id: ";
    public static final String POST_EXISTENTE = "Ya existe una publicacion con ese id";
    public static final String DATE_NOT_VALID = "La fecha de la publicación debe ser superior a la actual.";

    /*Formatos*/
    public static final String FORMATO_FECHA = "dd-MM-yyyy";
    public static final String ZONA_HORARIA = "America/Mexico_City";
    public static final int PERIODO_PUBLICACION = -2;
    public static final String ORDERNAMIENTO_ASC = "name_asc";
    public static final String ORDERNAMIENTO_DATE_ASC = "date_asc";

}

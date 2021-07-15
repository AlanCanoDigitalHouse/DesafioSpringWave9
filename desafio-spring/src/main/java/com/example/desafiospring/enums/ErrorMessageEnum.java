package com.example.desafiospring.enums;

public class ErrorMessageEnum {

    //PostCreateDto
    public static final String POST_USERID_MIN = "El user id no puede ser negativo";
    public static final String POST_USERID_NOT_NULL = "El user id es obligatorio";
    public static final String POST_DATE_NOT_NULL = "La fecha es obligatoria";
    public static final String POST_DATE_NOT_BLANK = "La fecha no puede ser vacia";
    public static final String POST_DETAIL_NOT_NULL = "El detalle de la publicacion es obligatorio";
    public static final String POST_CATEGORY_NOT_NULL = "La categoria es obligatoria";
    public static final String POST_CATEGORY_MIN = "El user id no puede ser negativo";
    public static final String POST_CATEGORY_MAX = "No existe una categoria mayor a 100";
    public static final String POST_PRICE_NOT_NULL = "El precio de la publicacion es obligatorio";
    public static final String POST_PRICE_MIN = "El precio no puede ser negativo";

    //PostPromoCreateDto
    public static final String POST_PROMO_HASPROMO_NOT_NULL =
            "Es necesario indicar si la publicacion es una promocion o no";

    //ProductCreateDto
    public static final String PRODUCT_NAME_NOT_NULL = "El nombre del producto es obligatorio";
    public static final String PRODUCT_NAME_NOT_BLANK = "El nombre del producto no puede ser vacio";
    public static final String PRODUCT_TYPE_NOT_NULL = "El tipo del producto es obligatorio";
    public static final String PRODUCT_TYPE_NOT_BLANK = "El tipo del producto no puede ser vacio";
    public static final String PRODUCT_BRAND_NOT_NULL = "La marca del producto es obligatoria";
    public static final String PRODUCT_BRAND_NOT_BLANK = "La marca del producto no puede ser vacia";
    public static final String PRODUCT_COLOR_NOT_NULL = "El color del producto es obligatorio";
    public static final String PRODUCT_COLOR_NOT_BLANK = "El color del producto no puede ser vacio";
    public static final String PRODUCT_NOTES_NOT_NULL = "El producto debe tener alguna nota";
    public static final String PRODUCT_NOTES_NOT_BLANK = "El nota del producto no puede ser vacia";

    //ExceptionControllerAdvice
    public static final String METHOD_ARGUMENT_TYPE_MISMATCH = "'%s' deberia ser un '%s' valido, y '%s' no lo es";
    public static final String INVALID_FORMAT = "'%s' deberia ser de tipo '%s' valido";
    public static final String IO_EXCEPTION = "Hubo un problema leyendo la base de datos";
    public static final String VALIDATION_ERROR = "Validations error";
    public static final String JSON_PARSER = "Hay un valor invalido para el atributo ";
    public static final String JSON_PARSER_IO = "No se ha podido leer el json de la petición";

    //Controllers
    public static final String CONTROLLER_USERID_MIN = "El id no puede ser negativo";

    //Services
    public static final String ALREADY_FOLLOWED_EXCEPTION = "El usuario %s ya esta siguiendo al usuario %s";
    public static final String SAME_USER_EXCEPTION = "Un usuario no puede seguirse a si mismo";
    public static final String USER_NOT_FOLLOWED_EXCEPTION = "El usuario %s NO esta siguiendo al usuario %s";
    public static final String PROMO_POST_INVALID_EXCEPTION =
            "La publicacion en promocion debe tener un valor de descuento";
    public static final String DISCOUNT_INVALID_EXCEPTION_NEGATIVE =
            "El valor del descuento no puede ser negativo";
    public static final String DISCOUNT_INVALID_EXCEPTION_MAX =
            "El valor del descuento no puede superar el 100% (valor > 1)";
    public static final String USER_NOT_EXIST_EXCEPTION = "No existe un vendedor con el id %s";

    //Utils
    public static final String DATE_INVALID_EXCEPTION_VALUE = "La fecha %s no es valida";
    public static final String DATE_INVALID_EXCEPTION_FUTURE = "La fecha %s no puede ser mayor a la fecha actual";

}

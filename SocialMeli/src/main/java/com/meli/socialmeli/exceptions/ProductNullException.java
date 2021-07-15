package com.meli.socialmeli.exceptions;


import com.meli.socialmeli.utils.Constant;
import org.springframework.http.HttpStatus;

public class ProductNullException extends SocialMeliExceptions{
    public ProductNullException(Integer id) {
        super(Constant.INFORMACION_PRODUCTO_INEXISTENTE.concat(id.toString()), HttpStatus.BAD_REQUEST);
    }
}

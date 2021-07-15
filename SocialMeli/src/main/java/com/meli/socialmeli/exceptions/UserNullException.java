package com.meli.socialmeli.exceptions;


import com.meli.socialmeli.utils.Constant;
import org.springframework.http.HttpStatus;

public class UserNullException extends SocialMeliExceptions{
    public UserNullException(Integer id) {
        super(Constant.INFORMACION_INEXISTENTE.concat(id.toString()), HttpStatus.BAD_REQUEST);
    }
}

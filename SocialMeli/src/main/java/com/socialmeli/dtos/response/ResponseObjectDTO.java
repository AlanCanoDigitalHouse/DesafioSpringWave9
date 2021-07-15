package com.socialmeli.dtos.response;

import com.socialmeli.dtos.ResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseObjectDTO extends ResponseDTO {

    private Object response;

    public ResponseObjectDTO(Integer status, String phrase, String message, Object response) {
        super(status, phrase, message);
        this.response = response;
    }
}

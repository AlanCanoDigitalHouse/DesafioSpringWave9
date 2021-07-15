package com.jbianchini.meli.socialmeli.dto.response;

import org.springframework.http.HttpStatus;

public class SuccessResponseDTO extends ResponseDTO {

    public SuccessResponseDTO(String message, Object dto) {
        super(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), message, dto);
    }
}

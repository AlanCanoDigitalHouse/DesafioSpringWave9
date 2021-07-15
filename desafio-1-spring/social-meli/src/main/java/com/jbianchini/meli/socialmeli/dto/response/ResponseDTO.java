package com.jbianchini.meli.socialmeli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private Integer status;
    private String phrase;
    private String message;
    private Object dto;

}

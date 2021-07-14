package com.example.socialmeli.dtos.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class ExceptionResponseDTO {

    private Integer status;
    private String msg;
    private Map<String, String> details;

}

package com.meli.itbootcamp.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class ExceptionDTO {
    private Integer status;
    private String messge;
    private Map<String, String> details;
}

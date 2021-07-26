package com.meli.bootcamp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.Map;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDTO {
    private final Integer status_code;
    private final String message;
    private final Map<String, String> details;
}

package com.mercadolibre.calculadorametroscuadrados.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Map;

@Getter
@AllArgsConstructor
public class FieldErrorDto{
    private Integer status;
    private String error;
    private Map<String, String> message;


}

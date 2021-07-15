package com.mercadolibre.socialmedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldErrorDto {
    private Integer status;
    private String error;
    private Map<String, String> message;;

}

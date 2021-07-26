package com.mercadolibre.calculadorametroscuadrados.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* TODO: Para respuesta de petición elemento ambiente, en lista,
    retorna la información requerida por el cliente*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnvironmentResponseDTO {
    private String environment_name;
    private Double environment_area;
}

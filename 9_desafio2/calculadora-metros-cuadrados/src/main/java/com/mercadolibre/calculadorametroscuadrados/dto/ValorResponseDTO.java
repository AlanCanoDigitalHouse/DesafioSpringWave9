package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValorResponseDTO {
    private Integer codigo;
    private String estatus;
    private String dato;
    private Object valor;
}

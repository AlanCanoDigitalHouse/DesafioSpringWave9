package com.mercadolibre.squaremeter.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoEnvironments {
    private String name;
    private Double square_meter;
}

package com.mercadolibre.calculadorametroscuadrados.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnvironmentModel {
    private String environment_name;
}

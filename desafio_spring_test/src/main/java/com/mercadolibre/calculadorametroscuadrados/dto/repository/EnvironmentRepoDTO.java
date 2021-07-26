package com.mercadolibre.calculadorametroscuadrados.dto.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* TODO: Para guardar en repositorio, elemento tipo ambiente,
 *   toda la información del ambiente*/

@Getter
@Setter
@NoArgsConstructor
public class EnvironmentRepoDTO {
    private String environment_name;
    private Double environment_width;
    private Double environment_length;
    private Double environment_area;

    public EnvironmentRepoDTO(String environment_name, Double environment_width, Double environment_length, Double environment_area) {
        this.environment_name = environment_name;
        this.environment_width = environment_width;
        this.environment_length = environment_length;
        this.environment_area = environment_area;
    }
}

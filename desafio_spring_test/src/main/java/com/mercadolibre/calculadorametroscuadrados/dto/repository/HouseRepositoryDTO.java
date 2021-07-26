package com.mercadolibre.calculadorametroscuadrados.dto.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/* TODO: Para respuesta de peticón, elemento tipo casa,
 *   toda la información de la casa*/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseRepositoryDTO {
    private String prop_name;
    private String district_name;
    private Double district_price;
    private Double prop_price;
    private Double prop_area;
    private List<EnvironmentRepoDTO> environments;
}

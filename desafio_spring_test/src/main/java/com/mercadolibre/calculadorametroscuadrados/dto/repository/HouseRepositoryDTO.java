package com.mercadolibre.calculadorametroscuadrados.dto.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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

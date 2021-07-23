package com.mercadolibre.calculadorametroscuadrados.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDAO {
    private String district_name;
    private Double district_price;
}

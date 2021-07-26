package com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DistrictResponseDto {

    private String district_name;
    private Double district_price;
}

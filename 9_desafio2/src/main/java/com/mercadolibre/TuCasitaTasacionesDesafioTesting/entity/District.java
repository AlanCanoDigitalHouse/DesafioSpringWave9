package com.mercadolibre.TuCasitaTasacionesDesafioTesting.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Data
@ToString
@Validated
@NoArgsConstructor
@AllArgsConstructor

public class District {

    private String district_name;
    private Double district_price;
}

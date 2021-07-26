package com.mercado_libre.bootcamp.desafio2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Neighborhood {
    private String district_name;
    private Double district_price;
}

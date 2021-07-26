package com.mercado_libre.bootcamp.desafio2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Neighborhood {
    private String name;
    private double price;
}

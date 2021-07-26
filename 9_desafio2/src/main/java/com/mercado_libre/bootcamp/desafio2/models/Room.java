package com.mercado_libre.bootcamp.desafio2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Room {
    private String name;
    private double width;
    private double height;
}

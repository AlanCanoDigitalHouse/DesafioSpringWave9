package com.mercado_libre.bootcamp.spring.desafio.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Relation {
    private Seller seller;
    private User follower;
}

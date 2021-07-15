package com.mercado_libre.bootcamp.spring.desafio.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class Seller extends User {
    @Getter
    @Setter
    private List<Post> posts;
}

package com.mercado_libre.bootcamp.spring.desafio.models;

import lombok.Data;

import java.util.List;

@Data
public class Seller extends User {
    private List<Post> posts;
}

package com.mercado_libre.bootcamp.spring.desafio.models;

import lombok.Data;

@Data
public abstract class Usuario {
    private int userId;
    private String userName;

    public abstract String getUserName();
}

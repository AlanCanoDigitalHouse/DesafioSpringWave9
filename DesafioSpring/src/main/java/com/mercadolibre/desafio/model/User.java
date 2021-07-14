package com.mercadolibre.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class User {

    private Integer userID;
    private String userName;
    private List<Integer> followers;
    private List<Integer> followed;
    private List<Integer> posts;
}

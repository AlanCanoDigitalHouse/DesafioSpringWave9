package com.example.DesafioSpring.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Seller extends Users{

    private List<Buyer> followers;

    public Seller(Integer id, String name) {
        super(id, name);
    }
}

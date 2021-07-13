package com.example.desafio1.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Seller extends User{
    private List<Client> followers;
    private List<Product> products;

    public Seller(Integer userId, String userName){
        super(userId, userName);
        this.followers = new ArrayList<>();
        this.products = new ArrayList<>();
    }
}

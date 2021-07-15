package com.example.desafio1.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Seller extends User{

    public Seller(Integer userId, String userName){
        super(userId, userName);
    }
}

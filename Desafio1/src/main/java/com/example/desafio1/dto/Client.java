package com.example.desafio1.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Client extends User{
    private List<Vendor> follows;

    public Client(Integer userId, String userName) {
        super(userId, userName);
        this.follows = new ArrayList<>();
    }
}

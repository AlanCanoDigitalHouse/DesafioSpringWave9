package com.example.desafio1.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Client extends User{

    public Client(Integer userId, String userName) {
        super(userId, userName);
    }
}

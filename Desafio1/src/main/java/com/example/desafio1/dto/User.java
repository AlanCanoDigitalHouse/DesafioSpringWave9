package com.example.desafio1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class User {
    private Integer userId;
    private String userName;
}

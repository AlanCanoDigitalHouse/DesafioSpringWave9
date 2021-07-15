package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class JsonDatabaseUsersDto {

    @Getter
    private List<User> users;


}

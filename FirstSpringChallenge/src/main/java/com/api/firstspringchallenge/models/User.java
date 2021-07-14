package com.api.firstspringchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class User {

    private String username;
    private int userId;

}

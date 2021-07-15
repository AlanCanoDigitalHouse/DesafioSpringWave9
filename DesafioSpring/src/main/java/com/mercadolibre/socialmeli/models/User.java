package com.mercadolibre.socialmeli.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Integer userID;
    private String userName;
    private List<User> followed;
    private List<User> followers;

    public User(Integer userId, String userName) {
        this.userID = userId;
        this.userName = userName;
        this.followed = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

}

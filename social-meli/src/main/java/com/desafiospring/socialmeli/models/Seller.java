package com.desafiospring.socialmeli.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends User{

    private List<User> followers;

    public Seller(int userId, String userName) {
        super(userId, userName);
        this.followers = new ArrayList<>();
    }
}

package com.desafiospring.socialmeli.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buyer extends User{

    private List<User> followed;

    public Buyer(int userId, String userName) {
        super(userId, userName);
        this.followed = new ArrayList<>();
    }
}

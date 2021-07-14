package com.desafiospring.socialmeli.dtos.models;

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

    public Buyer(Integer userId, String userName) {
        super(userId, userName);
        this.followed = new ArrayList<>();
    }
}

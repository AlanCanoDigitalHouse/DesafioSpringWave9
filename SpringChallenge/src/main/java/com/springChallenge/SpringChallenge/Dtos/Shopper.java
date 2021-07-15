package com.springChallenge.SpringChallenge.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Shopper extends User {
 private List<User> followed;

    public Shopper(int userId, String userName) {
        super(userId, userName);
        this.followed = new ArrayList<>();
    }

}

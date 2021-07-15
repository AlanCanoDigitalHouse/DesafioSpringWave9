package com.springChallenge.SpringChallenge.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Seller extends User {
    private List<User> followers;

    public Seller(){
        this.followers = new ArrayList<>();
    }

    public Seller(int userId, String userName) {
        super(userId, userName);
        this.followers = new ArrayList<>();
    }

}

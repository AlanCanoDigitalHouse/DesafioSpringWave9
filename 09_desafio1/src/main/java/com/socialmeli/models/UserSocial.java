package com.socialmeli.models;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSocial extends User {

    private ArrayList<User> followers;
    private ArrayList<User> followed;

    public UserSocial(int id, String name) {
        super(id, name);
        this.followers = new ArrayList<>();
        this.followed = new ArrayList<>();
    }
}

package com.meli.socialmeli.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String userName;

    public User(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
    }

    @Override
    public String toString(){
        return "{\n" + "userId: " + this.userId +
                "\n" + "userName: " + this.userName +
                "\n}";
    }
}

package com.meli.socialmeli.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private int userId;
    private String userName;

    @Override
    public String toString(){
        return "{\n" + "userId: " + this.userId +
                "\n" + "userName: " + this.userName +
                "\n}";
    }
}

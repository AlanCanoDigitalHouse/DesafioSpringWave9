package com.meli.socialmeli.models;

import lombok.Setter;

@Setter
public class Follow {
    private int followedUserId;
    private int followerUserId;

    @Override
    public String toString(){
        return "{\n" + "followedUserId: " + this.followedUserId +
                "\n" + "followerUserId: " + this.followerUserId +
                "\n}";
    }
}

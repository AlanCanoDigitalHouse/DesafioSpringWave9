package com.meli.socialmeli.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    private int toFollowUserId;
    private int followerUserId;

    @Override
    public String toString(){
        return "{\n" + "toFollowUserId: " + this.toFollowUserId +
                "\n" + "followerUserId: " + this.followerUserId +
                "\n}";
    }
}

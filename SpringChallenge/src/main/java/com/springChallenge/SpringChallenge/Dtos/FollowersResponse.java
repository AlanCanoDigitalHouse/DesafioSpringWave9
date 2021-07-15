package com.springChallenge.SpringChallenge.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowersResponse extends User{

    private int followers_count;
    public FollowersResponse(int userId, String userName, int followers_count) {
        super(userId, userName);
        this.followers_count = followers_count;
    }
}

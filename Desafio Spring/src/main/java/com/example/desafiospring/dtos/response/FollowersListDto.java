package com.example.desafiospring.dtos.response;

import com.example.desafiospring.dtos.createData.Users;

import java.util.List;

public class FollowersListDto {
    private int userId;
    private String userName;
    private List<Users> followers;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Users> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Users> followers) {
        this.followers = followers;
    }

    public FollowersListDto(int userId, String userName, List<Users> followers) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
    }

    public FollowersListDto() {
    }
}

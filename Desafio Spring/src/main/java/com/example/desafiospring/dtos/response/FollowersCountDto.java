package com.example.desafiospring.dtos.response;

public class FollowersCountDto {
    private int userId;
    private String userName;
    private int followers_count;

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

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public FollowersCountDto(int userId, String userName, int followers_count) {
        this.userId = userId;
        this.userName = userName;
        this.followers_count = followers_count;
    }

    public FollowersCountDto() {
    }
}

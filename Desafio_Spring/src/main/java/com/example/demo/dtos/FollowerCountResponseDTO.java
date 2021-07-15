package com.example.demo.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;

public class FollowerCountResponseDTO {

    private Integer userId;
    private String userName;

    @JsonProperty("followers_count")
    private Integer followersCount;

    public FollowerCountResponseDTO(Integer userId, String userName, Integer followersCount) {
        this.userId = userId;
        this.userName = userName;
        this.followersCount = followersCount;
    }

    public FollowerCountResponseDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    @Override
    public String toString() {
        return "FollowerCountResponseDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", followers_count=" + followersCount +
                '}';
    }
}

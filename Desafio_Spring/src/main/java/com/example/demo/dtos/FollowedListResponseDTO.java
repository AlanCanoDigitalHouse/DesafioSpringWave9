package com.example.demo.dtos;

import java.util.List;

public class FollowedListResponseDTO {
    private Integer userId;
    private String userName;
    private List<UserDTO> followed;

    public FollowedListResponseDTO() {
    }

    public FollowedListResponseDTO(Integer userId, String userName, List<UserDTO> followed) {
        this.userId = userId;
        this.userName = userName;
        this.followed = followed;
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

    public List<UserDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<UserDTO> followed) {
        this.followed = followed;
    }

    @Override
    public String toString() {
        return "FollowedListResponse{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", followed=" + followed +
                '}';
    }
}

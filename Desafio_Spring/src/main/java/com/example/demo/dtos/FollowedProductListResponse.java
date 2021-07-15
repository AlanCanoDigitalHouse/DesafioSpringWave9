package com.example.demo.dtos;

import java.util.List;

public class FollowedProductListResponse {

    private Integer userId;
    private List<PostDTO> posts;

    public FollowedProductListResponse(Integer userId, List<PostDTO> posts) {
        this.userId = userId;
        this.posts = posts;
    }

    public FollowedProductListResponse() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "FollowedProductListResponse{" +
                "userId=" + userId +
                ", posts=" + posts +
                '}';
    }
}

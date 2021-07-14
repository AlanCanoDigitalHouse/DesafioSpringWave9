package com.example.desafiospring.dtos.response;

import com.example.desafiospring.dtos.request.NewPostDto;

import java.util.List;

public class UserSeller2WeeksListDto {
    private int userId;
    private List<NewPostDto> posts;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<NewPostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<NewPostDto> posts) {
        this.posts = posts;
    }

    public UserSeller2WeeksListDto(int userId, List<NewPostDto> posts) {
        this.userId = userId;
        this.posts = posts;
    }

    public UserSeller2WeeksListDto() {
    }
}

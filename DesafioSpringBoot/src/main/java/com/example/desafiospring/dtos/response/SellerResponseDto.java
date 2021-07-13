package com.example.desafiospring.dtos.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SellerResponseDto extends UserResponseDto {
    private List<UserResponseDto> followers;

    public SellerResponseDto(int id, String userName) {
        super(id, userName);
        this.followers = new ArrayList<>();
    }

    public void addFollower(UserResponseDto client) {
        this.followers.add(client);
    }

    public void removeFollower(UserResponseDto client) {
        this.followers.remove(client);
    }

    public void setFollowers(List<UserResponseDto> followers) {
        this.followers = followers;
    }
}

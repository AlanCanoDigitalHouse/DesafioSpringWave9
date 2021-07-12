package com.example.desafiospring.dtos.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class ClientResponseDto extends UserResponseDto{
    private List<UserResponseDto> followed;
    public ClientResponseDto(int id, String userName) {
        super(id, userName);
        this.followed = new ArrayList<>();
    }
    public void addFollowed(UserResponseDto seller){
        this.followed.add(seller);
    }
}

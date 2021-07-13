package com.example.desafiospring.dtos.response;

import lombok.Getter;
import org.apache.catalina.User;

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
    public void removeFollowed(UserResponseDto seller){
        this.followed.remove(seller);
    }
}

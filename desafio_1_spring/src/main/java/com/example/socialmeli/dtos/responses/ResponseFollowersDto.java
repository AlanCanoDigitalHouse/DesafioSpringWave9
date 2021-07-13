package com.example.socialmeli.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
public class ResponseFollowersDto {
    private Integer userId;
    private String username;
    private ArrayList<ResponseUserDto> followers;
}

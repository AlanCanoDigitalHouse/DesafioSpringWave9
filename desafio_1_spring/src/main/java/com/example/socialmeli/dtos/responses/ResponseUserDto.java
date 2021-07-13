package com.example.socialmeli.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseUserDto {
    private Integer userId;
    private String userName;
}

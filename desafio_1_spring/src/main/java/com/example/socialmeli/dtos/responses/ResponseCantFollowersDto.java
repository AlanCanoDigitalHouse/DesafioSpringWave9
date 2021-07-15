package com.example.socialmeli.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCantFollowersDto extends ResponseUserDto{
    private Integer followersCount;

}

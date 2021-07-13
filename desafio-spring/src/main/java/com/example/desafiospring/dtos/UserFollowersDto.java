package com.example.desafiospring.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFollowersDto {

    private Long userId;
    private String userName;
    private Long followers_count;
    private List<UserBasicDto> followers;
    private List<UserBasicDto> followed;

    public UserFollowersDto(Long userId, String userName, Long followers_count) {
        this.userId = userId;
        this.userName = userName;
        this.followers_count = followers_count;
    }

    public UserFollowersDto(Long userId, String userName, List<UserBasicDto> followers, List<UserBasicDto> followed) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
        this.followed = followed;
    }
}

package com.meli.desafiospring.DTOs.response;

import com.meli.desafiospring.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowersCountResponseDTO {

    Integer userId;
    String userName;
    Integer followers_count;

    public FollowersCountResponseDTO(User user, Integer followers_count) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.followers_count = followers_count;
    }
}

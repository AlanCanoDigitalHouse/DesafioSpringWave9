package com.apismeli.socialmeli.dtos.response;

import com.apismeli.socialmeli.dtos.request.UserDTO;
import lombok.Data;

@Data
public class CountFollowersDTO extends UserDTO {
    private Integer followers_count;

    public CountFollowersDTO(Integer userId, String userName, Integer followers_count) {
        super(userId, userName);
        this.followers_count = followers_count;
    }

    public CountFollowersDTO(Integer followers_count) {
        this.followers_count = followers_count;
    }
}

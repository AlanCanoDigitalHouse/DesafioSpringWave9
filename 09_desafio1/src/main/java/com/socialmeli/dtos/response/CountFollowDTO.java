package com.socialmeli.dtos.response;

import com.socialmeli.dtos.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountFollowDTO extends UserDTO {

    private Integer followers_count;

    public CountFollowDTO(Integer userId, String userName, Integer followers_count) {
        super(userId, userName);
        this.followers_count = followers_count;
    }
}

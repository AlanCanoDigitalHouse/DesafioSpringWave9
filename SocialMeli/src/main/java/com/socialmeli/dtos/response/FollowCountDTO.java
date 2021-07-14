package com.socialmeli.dtos.response;

import com.socialmeli.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class FollowCountDTO extends UserDTO {

    private Integer followers_count;

    public FollowCountDTO(Integer userId, String userName, Integer followers_count) {
        super(userId, userName);
        this.followers_count = followers_count;
    }
}

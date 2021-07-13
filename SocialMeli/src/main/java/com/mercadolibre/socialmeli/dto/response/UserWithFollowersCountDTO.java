package com.mercadolibre.socialmeli.dto.response;

import com.mercadolibre.socialmeli.entity.UserBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
public class UserWithFollowersCountDTO extends UserBase {
    int followers_count;

    public UserWithFollowersCountDTO(int userId, String userName, int followersCount){
        this.setUserId(userId);
        this.setUserName(userName);
        this.followers_count = followersCount;
    }
}

package com.mercadolibre.socialmeli.dto.response;

import com.mercadolibre.socialmeli.entity.UserBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserWithFollowedDTO extends UserBase {
    List<UserBase> followed;

    public UserWithFollowedDTO(int userId, String userName, List<UserBase> followed){
        this.followed = followed;
        this.setUserName(userName);
        this.setUserId(userId);
    }
}

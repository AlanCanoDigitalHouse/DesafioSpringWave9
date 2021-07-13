package com.mercadolibre.socialmeli.dto.response;

import com.mercadolibre.socialmeli.entity.UserBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserWithFollowersDTO extends UserBase {
    List<UserBase> followers;

    public UserWithFollowersDTO(int userId, String userName, List<UserBase> followers){
        this.followers = followers;
        this.setUserName(userName);
        this.setUserId(userId);
    }
}

package com.mercadolibre.socialmedia.dtos.response;

import com.mercadolibre.socialmedia.dtos.User;
import com.mercadolibre.socialmedia.dtos.UserDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class FollowersUsersResponse extends User {
    private List<User> followers;

    public FollowersUsersResponse(Integer userId, String userName, List<User> followers){
        super(userId, userName);
        this.followers = followers;
    }

    public List<User> getFollowers(){
        return this.followers;
    }

}

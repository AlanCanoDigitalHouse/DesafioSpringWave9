package com.mercadolibre.socialmeli.services.interfaces;

import com.mercadolibre.socialmeli.dtos.FollowedListDTO;
import com.mercadolibre.socialmeli.dtos.FollowersCountDTO;
import com.mercadolibre.socialmeli.dtos.FollowersListDTO;

public interface UserService {

    void follow(Integer userId, Integer userIdToFollow);

    void unfollow(Integer userId, Integer userIdToUnfollow);

    FollowersListDTO getFollowersList(Integer userId, String order);

    FollowedListDTO getFollowedList(Integer userId, String order);

    FollowersCountDTO getFollowersCount(Integer userId);


}

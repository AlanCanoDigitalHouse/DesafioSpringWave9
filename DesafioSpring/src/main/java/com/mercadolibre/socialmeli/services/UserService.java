package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.response.UserFollowedListResponseDTO;
import com.mercadolibre.socialmeli.dtos.response.UserFollowersCountResponseDTO;
import com.mercadolibre.socialmeli.dtos.response.UserFollowersListResponseDTO;
import com.mercadolibre.socialmeli.models.User;

import java.util.Optional;

public interface UserService {

    void follow(Integer userId, Integer userIdToFollow);

    void unFollow(Integer userId, Integer userIdToFollow);

    User addUser(User user);

    UserFollowersCountResponseDTO followersCount(Integer userId);

    UserFollowersListResponseDTO followersList(Integer userId, String order);

    UserFollowedListResponseDTO followedList(Integer userId, String order);

    Optional<User> findUserByUserId(Integer userId);

    boolean userExist(Integer userId);

    void init();
}

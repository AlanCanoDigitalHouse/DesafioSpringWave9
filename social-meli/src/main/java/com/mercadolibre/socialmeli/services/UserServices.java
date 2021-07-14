package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dto.User;
import com.mercadolibre.socialmeli.dto.respons.UserFollowedListResponse;
import com.mercadolibre.socialmeli.dto.respons.UserFollowersListResponse;
import com.mercadolibre.socialmeli.exceptions.ExpectationFailed;
import com.mercadolibre.socialmeli.exceptions.UserBadRequest;

import java.util.List;

public interface UserServices {
    Object postFollow(int userid, int userIdToFollow) throws UserBadRequest, ExpectationFailed;

    Object getCountFollowers(int userid) throws UserBadRequest;

    UserFollowersListResponse getAllFollowers(int userid, String order) throws UserBadRequest;

    UserFollowedListResponse getAllFollowed(int userid, String order) throws UserBadRequest;

    Object postUnfollow(int userid, int userIdToFollow) throws UserBadRequest;

    List<User> getAllUsers();
}

package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dto.UserToUser;
import com.mercadolibre.socialmeli.exceptions.ExpectationFailed;
import com.mercadolibre.socialmeli.exceptions.UserBadRequest;

import java.util.List;

public interface UserToUserService {
    Object postFollow(int userid, int userIdToFollow) throws ExpectationFailed, UserBadRequest;

    Object postUnfollow(int userid, int userIdToFollow) throws ExpectationFailed, UserBadRequest;

    List<UserToUser> getAllRelationByUserFollowers(int userid);

    List<UserToUser> findAllRelationByUserFollowed(int user);


}

package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.dto.UserToUser;

import java.util.List;

public interface UserToUserRepository {
    List<UserToUser> findAllRelationByUserFollowers(int user);

    List<UserToUser> findAllRelationByUserFollowed(int user);

    void addFollowUser(int user, int userF);

    void deleteRelation(int user, int userF);

    boolean relationExist(int user, int userF);
}

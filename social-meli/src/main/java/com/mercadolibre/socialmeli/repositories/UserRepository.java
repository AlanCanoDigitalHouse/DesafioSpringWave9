package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.dto.User;
import com.mercadolibre.socialmeli.dto.UserToUser;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

   List<User> findAllUser();

   User findUserById(int user);

   List<User> findFollowedByUser(int user);

   List<User> findFollowersByUser(int user);

   void addFollowUser(int user, int userF);

   List<UserToUser> findAllRelationByUserFollowers(int user);

   List<UserToUser> findAllRelationByUserFollowed(int user);

   void deleteRelation(int user, int userF);

   boolean relationExist(int user, int userF);

}
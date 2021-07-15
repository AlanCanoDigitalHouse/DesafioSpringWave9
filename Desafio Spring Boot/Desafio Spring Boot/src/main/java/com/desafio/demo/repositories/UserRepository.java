package com.desafio.demo.repositories;

import com.desafio.demo.entiity.User;

public interface UserRepository {
    void saveFollow(Integer userId, Integer userIdToFollow);

    User getUser(Integer userId);

    void deleteFollow(int userId, int userIdToUnfollow);

    void initilize();
}

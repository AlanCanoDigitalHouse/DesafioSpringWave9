package com.desafio.spring.repositories;

import com.desafio.spring.dtos.UserDto;

import java.util.Collection;
import java.util.List;

public interface UserRepository {

    void addNew(Integer userId, Integer userIdToFollow);
    void deleteFollower(Integer userId, Integer userIdToUnfollow);
    UserDto findUser(Integer userId);
    List<UserDto> allFollowers(Integer userId, String order);
    List<UserDto> allFollowed(Integer userId, String order);

    Collection<Object> allFollowers(Integer userId);
}

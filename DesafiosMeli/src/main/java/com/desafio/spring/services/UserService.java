package com.desafio.spring.services;

import com.desafio.spring.dtos.UserDto;
import java.util.List;

public interface UserService {

    void addFollower(Integer userId, Integer userIdToFollow);
    void deleteFollower(Integer userId, Integer userIdToFollow);
    Integer totalFollowers(Integer userId);
    List<UserDto> allFollowers(Integer userId,String order);
    List<UserDto> allFollowed(Integer userId, String order);
}

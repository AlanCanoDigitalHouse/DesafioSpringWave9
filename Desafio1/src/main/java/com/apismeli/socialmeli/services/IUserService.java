package com.apismeli.socialmeli.services;

import com.apismeli.socialmeli.dtos.request.MarketDTO;
import com.apismeli.socialmeli.dtos.response.CountFollowersDTO;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    MarketDTO initializer();

    ResponseEntity toFollow(Integer userId, Integer userIdtoFollow) throws Exception;

    CountFollowersDTO countFollowers(Integer userId) throws Exception;

    ResponseEntity unfollow(Integer userId, Integer userIdToUnfollow) throws Exception;

    Object showUsers(Integer userID, String follow, String order) throws Exception;
}

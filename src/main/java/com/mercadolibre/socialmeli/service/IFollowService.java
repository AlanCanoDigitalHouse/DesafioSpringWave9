package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.request.FollowRequest;
import com.mercadolibre.socialmeli.dto.response.CountFollowersResponse;
import com.mercadolibre.socialmeli.dto.response.UserFollowedResponse;
import com.mercadolibre.socialmeli.dto.response.UserFollowersResponse;
import com.mercadolibre.socialmeli.entity.Follow;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFollowService {
    public ResponseEntity createFollow(FollowRequest followRequest);
    public Integer countFollow(Integer userId);
    public List<Integer> listUserFollower(Integer userId);
    public List<Integer> listUserFollowed(Integer userId);

    public UserFollowedResponse listUserIdFollowed(Integer userId);

    public List<Follow> findAll();

    public ResponseEntity findByUserIdAndUserIdToFollow(Integer userId, Integer userIdToFollow);

    public CountFollowersResponse countFollowers(Integer userId);
    //ooo3
    public UserFollowersResponse followers(Integer userId , String order);
}

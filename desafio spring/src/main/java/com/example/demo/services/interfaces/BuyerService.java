package com.example.demo.services.interfaces;

import com.example.demo.dtos.response.ListFollowedResponseDto;
import com.example.demo.models.User;

import java.util.List;
import java.util.Map;

public interface BuyerService {

    public void followSeller(User userId, User userToFollow);

    public ListFollowedResponseDto listFollowed(User user, Map<Integer,User> users, String order);

    public void unfollowSeller(User user, User user1);
}

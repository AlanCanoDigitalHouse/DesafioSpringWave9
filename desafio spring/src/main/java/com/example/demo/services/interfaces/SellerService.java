package com.example.demo.services.interfaces;

import com.example.demo.dtos.response.CountFollowersResponseDto;
import com.example.demo.dtos.response.ListFollowersResponseDto;
import com.example.demo.models.User;

import java.util.List;
import java.util.Map;

public interface SellerService {

    public CountFollowersResponseDto countFollowers(User user);

    public ListFollowersResponseDto listFollowers(User user, Map<Integer,User> usersList, String order);

}

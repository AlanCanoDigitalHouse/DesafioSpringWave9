package com.example.desafiospring.services.interfaces;

import java.util.List;

public interface FollowService {
    void addNewFollow(Integer followerUserId, Integer followedUserId);

    List<Integer> getFollowerIDs(Integer userId);

    List<Integer> getFollowedIDs(Integer userId);

    void deleteFollow(Integer followerUserId, Integer followedUserId);
}

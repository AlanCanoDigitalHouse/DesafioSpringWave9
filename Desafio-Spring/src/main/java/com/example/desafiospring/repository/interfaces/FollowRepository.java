package com.example.desafiospring.repository.interfaces;

import java.util.List;

public interface FollowRepository {
    void addNewFollow(Integer followerUserId, Integer followedUserId);

    List<Integer> getFollowerIDs(Integer userId);

    List<Integer> getFollowedIDs(Integer userId);

    void deleteFollow(Integer followerUserId, Integer followedUserId);
}

package com.example.desafiospring.repositories;

import com.example.desafiospring.entities.User;

import java.util.List;

public interface IFollowerRepository {

    Long getNumFollowersById(Long userId);

    void followUserById(Long userId, Long userIdToFollow);

    List<Long> getListFollowersById(Long userId);

    List<Long> getListFollowedById(Long userId);

    void unfollowUserById(Long userId, Long userIdToUnfollow);

    boolean isFollowedByUserId(Long userId, Long userIdToFollow);

}

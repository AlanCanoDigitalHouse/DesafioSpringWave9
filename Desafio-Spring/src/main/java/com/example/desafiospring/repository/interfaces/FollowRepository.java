package com.example.desafiospring.repository.interfaces;

public interface FollowRepository {
    void addNewFollow(Integer userId, Integer userIdToFollow);
}

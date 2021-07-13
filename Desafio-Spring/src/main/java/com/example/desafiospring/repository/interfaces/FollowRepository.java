package com.example.desafiospring.repository.interfaces;

import java.util.Collection;
import java.util.List;

public interface FollowRepository {
    void addNewFollow(Integer userId, Integer userIdToFollow);

    List<Integer> getFollowerIDs(Integer userId);

    List<Integer> getFollowedIDs(Integer userId);
}

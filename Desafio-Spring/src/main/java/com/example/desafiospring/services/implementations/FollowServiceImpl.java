package com.example.desafiospring.services.implementations;

import com.example.desafiospring.repository.interfaces.FollowRepository;
import com.example.desafiospring.services.interfaces.FollowService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    FollowRepository followRepository;

    public FollowServiceImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public void addNewFollow(Integer followerUserId, Integer followedUserId) {
        followRepository.addNewFollow(followerUserId, followedUserId);
    }

    @Override
    public List<Integer> getFollowerIDs(Integer userId) {
        return followRepository.getFollowerIDs(userId);
    }

    @Override
    public List<Integer> getFollowedIDs(Integer userId) {
        return followRepository.getFollowedIDs(userId);
    }

    @Override
    public void deleteFollow(Integer followerUserId, Integer followedUserId) {
        followRepository.deleteFollow(followerUserId,followedUserId);
    }
}

package com.meli.desafiospring.services;

import com.meli.desafiospring.DTOs.response.FollowersListResponseDTO;
import com.meli.desafiospring.models.User;
import com.meli.desafiospring.models.sorters.SortByNameAsc;
import com.meli.desafiospring.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements IUserManager{

    UserRepository userRepo;

    @Override
    public HttpStatus follow(Integer userId, Integer userIdToFollow) {
        userRepo.follow(userId, userIdToFollow);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus unfollow(Integer userId, Integer userIdToUnfollow) {
        userRepo.unfollow(userId, userIdToUnfollow);
        return HttpStatus.OK;
    }

    @Override
    public User getUser(Integer userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public Integer followersCount(Integer userId) {
        return userRepo.followers_count(userId);
    }

    @Override
    public FollowersListResponseDTO followersList(Integer sellerId, String order) {
        User user = userRepo.findById(sellerId).get();
        List<User> followers = user.getFollowers();
        followers.sort(new SortByNameAsc());
        return new FollowersListResponseDTO(user.getUserId(), user.getUserName(), followers);
    }

    @Override
    public FollowersListResponseDTO followedList(Integer userId, String order) {
        User user = userRepo.findById(userId).get();
        List<User> followers = user.getFollowers();
        followers.sort(new SortByNameAsc());
        return new FollowersListResponseDTO(user.getUserId(), user.getUserName(), followers);
    }

    @Override
    public ResponseEntity<List<User>> initialize() {
        return new ResponseEntity<>(userRepo.initialize(), HttpStatus.OK);
    }


}

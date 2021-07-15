package com.example.demo.services;

import com.example.demo.cache.CacheConfiguration;
import com.example.demo.dtos.*;
import com.example.demo.exceptions.UserException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private CacheConfiguration cacheConfiguration;

    public void followSeller(int userId, int userIdToFollow){
        User user = cacheConfiguration.userCache.getIfPresent(userId);
        User userToFollow = cacheConfiguration.userCache.getIfPresent(userIdToFollow);
        if(user != null && userToFollow != null) {
            String userName = user.getUserName();
            String usernameToFollow = userToFollow.getUserName();
            UserDTO userDTO = new UserDTO(userId, userName);
            UserDTO userDTOToFollow = new UserDTO(userIdToFollow, usernameToFollow);
            user.getFollowed().add(userDTOToFollow);
            userToFollow.getFollowers().add(userDTO);
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public ResponseEntity<FollowerCountResponseDTO> getSellerFollowersCount(int userId){
        User user = cacheConfiguration.userCache.getIfPresent(userId);
        if(user != null){
            String userName = user.getUserName();
            int followersCount = user.getFollowers().size();
            return ResponseEntity.status(HttpStatus.OK).body(new FollowerCountResponseDTO(userId, userName, followersCount));
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public ResponseEntity<FollowerListResponseDTO> getFollowersList(int userId, String order){
        User user = cacheConfiguration.userCache.getIfPresent(userId);
        if(user != null){
            String userName = user.getUserName();
            List<UserDTO> followerList = user.getFollowers();
            orderFollowsByUserName(order, followerList);
            return ResponseEntity.status(HttpStatus.OK).body(new FollowerListResponseDTO(userId, userName, followerList));
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public ResponseEntity<FollowedListResponseDTO> getFollowedList(int userId, String order){
        User user = cacheConfiguration.userCache.getIfPresent(userId);
        if(user != null){
            String userName = user.getUserName();
            List<UserDTO> followedList = user.getFollowed();
            orderFollowsByUserName(order, followedList);
            return ResponseEntity.status(HttpStatus.OK).body(new FollowedListResponseDTO(userId, userName, followedList));
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public void unfollowUser(int userId, int userIdToUnfollow){
        User user = cacheConfiguration.userCache.getIfPresent(userId);
        User userToUnfollow = cacheConfiguration.userCache.getIfPresent(userIdToUnfollow);
        if(user != null && userToUnfollow != null){
            List<UserDTO> followedByUser = user.getFollowed();
            List<UserDTO> userToUnfollowFollowers = userToUnfollow.getFollowers();
            followedByUser.removeIf(f -> f.getUserId() == userIdToUnfollow);
            userToUnfollowFollowers.removeIf(f -> f.getUserId() == userId);
        } else {
            throw new UserException("User and UserToUnfollow must not be null!!");
        }
    }

    private void orderFollowsByUserName(String order, List<UserDTO> follows) {
        if(order != null){
            if(order.equals("name_asc")){
                follows.sort(Comparator.comparing(UserDTO::getUserName));
            } else if (order.equals("name_desc")) {
                follows.sort(Comparator.comparing(UserDTO::getUserName).reversed());
            } else {
                System.out.println("Invalid order parameter, order remains the same for all registers.");
            }
        }
    }
}
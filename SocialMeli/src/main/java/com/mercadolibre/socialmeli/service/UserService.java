package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.response.UserWithFollowedDTO;
import com.mercadolibre.socialmeli.dto.response.UserWithFollowersDTO;
import com.mercadolibre.socialmeli.dto.response.UserWithFollowersCountDTO;
import com.mercadolibre.socialmeli.entity.User;
import com.mercadolibre.socialmeli.entity.UserBase;
import com.mercadolibre.socialmeli.exception.UserAlreadyFollowSellerException;
import com.mercadolibre.socialmeli.exception.UserCannotFollowOneSelfException;
import com.mercadolibre.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    public UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void followUser(int userId, int userIdToFollow){
            User user = userRepository.findUserById(userId);
            User seller = userRepository.findUserById(userIdToFollow);
            if (user.getUserId() == seller.getUserId()) throw new UserCannotFollowOneSelfException();
            boolean isUserFollowingSeller = seller
                    .getFollowers()
                    .stream()
                    .anyMatch(
                            (follower) -> follower.equals(user.getUserId()));
            if (isUserFollowingSeller) throw new UserAlreadyFollowSellerException();

            user.getFollowed().add(userIdToFollow);
            seller.getFollowers().add(userId);
    }

    public void addPostToUser(int userId, int postId){
        userRepository.addPostToUser(userRepository.findUserById(userId), postId);
    }

    public UserWithFollowersCountDTO getUserWithFollowersCountDTO(int userId){
        User user = userRepository.findUserById(userId);
        return new UserWithFollowersCountDTO(
                user.getUserId(),
                user.getUserName(),
                user.getFollowers().size()
        );
    }

    public UserWithFollowersDTO getUserWithFollowers(int userId){
        User user = userRepository.findUserById(userId);
        List<UserBase> followers = userRepository.getFollowersByUser(user);
        return new UserWithFollowersDTO(user.getUserId(), user.getUserName(), followers);
    }

    public UserWithFollowedDTO getUSerWithFollowed(int userId){
        User user = userRepository.findUserById(userId);
        List<UserBase> followers = user.getFollowed().stream().map(
                item -> {
                    User followed = userRepository.findUserById(item);
                    return new UserBase(followed.getUserId(), followed.getUserName());
                }).collect(Collectors.toList());
        return new UserWithFollowedDTO(user.getUserId(), user.getUserName(), followers);
    }

}

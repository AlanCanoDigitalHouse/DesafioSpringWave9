package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.response.UserWithFollowedDTO;
import com.mercadolibre.socialmeli.dto.response.UserWithFollowersDTO;
import com.mercadolibre.socialmeli.dto.response.UserWithFollowersCountDTO;
import com.mercadolibre.socialmeli.entity.User;
import com.mercadolibre.socialmeli.entity.UserBase;
import com.mercadolibre.socialmeli.exception.UserAlreadyFollowSeller;
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
            if (isUserFollowingSeller) throw new UserAlreadyFollowSeller();

            user.getFollowed().add(userIdToFollow);
            seller.getFollowers().add(userId);
    }

    public UserWithFollowersCountDTO getUserWithFollowersCountDTO(int userId){
        User user = userRepository.findUserById(userId);
        return new UserWithFollowersCountDTO(
                user.getUserId(),
                user.getUserName(),
                user.getFollowers().size()
        );
    }

    public UserWithFollowersDTO getUSerWithFollowers(int userId){
        User user = userRepository.findUserById(userId);
        List<UserBase> followers = user.getFollowers().stream().map(
                item -> {
                    User follower = userRepository.findUserById(item);
                    return new UserBase(follower.getUserId(), follower.getUserName());
                }).collect(Collectors.toList());
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

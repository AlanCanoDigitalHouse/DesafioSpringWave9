package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.response.UserWithFollowedDTO;
import com.mercadolibre.socialmeli.dto.response.UserWithFollowersDTO;
import com.mercadolibre.socialmeli.dto.response.UserWithFollowersCountDTO;
import com.mercadolibre.socialmeli.entity.User;
import com.mercadolibre.socialmeli.entity.UserBase;
import com.mercadolibre.socialmeli.exception.UserAlreadyFollowSellerException;
import com.mercadolibre.socialmeli.exception.UserAlreadyUnFollowSellerException;
import com.mercadolibre.socialmeli.exception.UserCannotFollowOneSelfException;
import com.mercadolibre.socialmeli.exception.UserCannotUnFollowOneSelfException;
import com.mercadolibre.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
            boolean isUserFollowingSeller = seller.getFollowers().contains(user.getUserId());
            if (isUserFollowingSeller) throw new UserAlreadyFollowSellerException();
            user.getFollowed().add(userIdToFollow);
            seller.getFollowers().add(userId);
    }

    public void unFollowUser(int userId, int userIdToUnfollow){
        User user = userRepository.findUserById(userId);
        User seller = userRepository.findUserById(userIdToUnfollow);
        if (user.getUserId() == seller.getUserId()) throw new UserCannotUnFollowOneSelfException();
        boolean isUserUnFollowingSeller = seller.getFollowers().contains(user.getUserId());
        if (!isUserUnFollowingSeller) throw new UserAlreadyUnFollowSellerException();
        user.getFollowed().remove(user.getFollowed().indexOf(userIdToUnfollow));
        seller.getFollowers().remove(seller.getFollowers().indexOf(userId));
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

    public UserWithFollowersDTO getUserWithFollowers(int userId, String order){
        User user = userRepository.findUserById(userId);
        List<UserBase> followers = userRepository.getFollowersByUser(user, order);
        return new UserWithFollowersDTO(user.getUserId(), user.getUserName(), followers);
    }

    public UserWithFollowedDTO getUSerWithFollowed(int userId){
        User user = userRepository.findUserById(userId);
        List<UserBase> followers = userRepository.getFollowedByUser(user);
        return new UserWithFollowedDTO(user.getUserId(), user.getUserName(), followers);
    }

}

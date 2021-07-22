package com.api.firstspringchallenge.services.user.implementation;

import com.api.firstspringchallenge.dtos.response.FollowedResponseDTO;
import com.api.firstspringchallenge.dtos.response.FollowersCountResponseDTO;
import com.api.firstspringchallenge.dtos.response.FollowersResponseDTO;
import com.api.firstspringchallenge.dtos.response.UsersResponseDTO;
import com.api.firstspringchallenge.exceptions.UnexistingUserException;
import com.api.firstspringchallenge.manager.Manager;
import com.api.firstspringchallenge.models.Relation;
import com.api.firstspringchallenge.models.User;
import com.api.firstspringchallenge.repositories.user.implementation.UserRepository;
import com.api.firstspringchallenge.services.relation.implementation.RelationService;
import com.api.firstspringchallenge.services.user.UserServiceI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserServiceI {

    private final UserRepository repository;

    private final RelationService relationService;

    @Override
    public User findUserById(int userId) {
        validateUser(userId);
        return repository.findUserById(userId);
    }

    public void validateUser(int userId){
        if (!repository.isSeller(userId)) {
            throw new UnexistingUserException("invalid user id ("+userId+")");
        }
    }

    @Override
    public ResponseEntity<Void> follow(int userId, int otherUserId) {
        User follower = findUserById(userId);
        User followed = findUserById(otherUserId);
        relationService.createRelation(follower, followed);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getFollowersQuantity(int userId) {
        User seller = findUserById(userId);
        List<User> followers = relationService.getFollowers(seller);
        FollowersCountResponseDTO response = new FollowersCountResponseDTO(seller, followers.size());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getFollowers(int userId, String order) {
        User seller = findUserById(userId);
        List<User> followers = relationService.getFollowers(seller);
        List<User> ordered = Manager.orderUserBy(order, followers);
        FollowersResponseDTO response = new FollowersResponseDTO(seller, ordered);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getFollowed(int userId) {
        User seller = findUserById(userId);
        List<User> followers = relationService.getFollowed(seller);
        FollowedResponseDTO response = new FollowedResponseDTO(seller, followers);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> unfollow(int userId, int otherUserId) {
        User follower = findUserById(userId);
        User followed = findUserById(otherUserId);
        Relation relation = relationService.findRelation(follower, followed);
        relationService.deleteRelation(relation);

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getUsers() {
        UsersResponseDTO response = new UsersResponseDTO(repository.getUsers());
        return new ResponseEntity(response,HttpStatus.OK);
    }


}

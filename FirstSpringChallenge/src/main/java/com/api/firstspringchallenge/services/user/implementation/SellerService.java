package com.api.firstspringchallenge.services.user.implementation;

import com.api.firstspringchallenge.dtos.response.FollowedResponseDTO;
import com.api.firstspringchallenge.dtos.response.FollowersCountResponseDTO;
import com.api.firstspringchallenge.dtos.response.FollowersResponseDTO;
import com.api.firstspringchallenge.exceptions.DuplicatedFollowException;
import com.api.firstspringchallenge.exceptions.UnexistingUserException;
import com.api.firstspringchallenge.manager.Manager;
import com.api.firstspringchallenge.models.Relation;
import com.api.firstspringchallenge.models.Seller;
import com.api.firstspringchallenge.models.User;
import com.api.firstspringchallenge.repositories.seller.implementation.SellerRepository;
import com.api.firstspringchallenge.services.relation.implementation.RelationService;
import com.api.firstspringchallenge.services.user.SellerServiceI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SellerService implements SellerServiceI {

    private final SellerRepository repository;

    private final RelationService relationService;

    @Override
    public Seller findSellerById(int userId) {
        validateUser(userId);
        return repository.findSellerById(userId);
    }

    public void validateUser(int userId){
        if (!repository.isSeller(userId)) {
            throw new UnexistingUserException("invalid user id ("+userId+")");
        }
    }

    @Override
    public ResponseEntity<Void> follow(int userId, int otherUserId) {
        Seller follower = findSellerById(userId);
        Seller followed = findSellerById(otherUserId);
        relationService.createRelation(follower, followed);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getFollowersQuantity(int userId) {
        Seller seller = findSellerById(userId);
        List<User> followers = relationService.getFollowers(seller);
        FollowersCountResponseDTO response = new FollowersCountResponseDTO(seller, followers.size());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getFollowers(int userId, String order) {
        Seller seller = findSellerById(userId);
        List<User> followers = relationService.getFollowers(seller);
        List<User> ordered = Manager.orderUserBy(order, followers);
        FollowersResponseDTO response = new FollowersResponseDTO(seller, ordered);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getFollowed(int userId) {
        Seller seller = findSellerById(userId);
        List<User> followers = relationService.getFollowed(seller);
        FollowedResponseDTO response = new FollowedResponseDTO(seller, followers);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> unfollow(int userId, int otherUserId) {
        Seller follower = findSellerById(userId);
        Seller followed = findSellerById(otherUserId);
        Relation relation = relationService.findRelation(follower, followed);
        relationService.deleteRelation(relation);

        return new ResponseEntity(HttpStatus.OK);
    }


}

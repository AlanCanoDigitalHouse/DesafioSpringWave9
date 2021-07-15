package com.springChallenge.SpringChallenge.Services;

import com.springChallenge.SpringChallenge.Dtos.FollowersResponse;
import com.springChallenge.SpringChallenge.Dtos.Seller;
import com.springChallenge.SpringChallenge.Dtos.Shopper;
import com.springChallenge.SpringChallenge.Dtos.User;
import com.springChallenge.SpringChallenge.Repositories.IRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;

@Service
public class UserService implements IUserService {
    private final IRepository repository;

    public UserService(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public void follow(int userId, int userIdToFollow) {
        if(userId == userIdToFollow){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "A user cannot follow himself"
            );
        }
        repository.follow(userId, userIdToFollow);
    }

    @Override
    public void unfollow(int userId, int userIdToFollow) {
        if(userId == userIdToFollow){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "A user cannot follow himself"
            );
        }
        repository.unfollow(userId, userIdToFollow);
    }

    @Override
    public FollowersResponse followers(int userId) {
        Seller seller = repository.getSellerById(userId);
        if(seller == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Seller not exist"
            );
        }
        return new FollowersResponse(seller.getUserId(), seller.getUserName(), seller.getFollowers().size());
    }

    @Override
    public Seller getSellerById(int userId, String order) {
        Seller seller = repository.getSellerById(userId);
        if(seller == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Seller not found"
            );
        }
        if(order != null && order.equals("name_asc")){
            seller.getFollowers().sort(Comparator.comparing(User::getUserName));
        }
        if(order != null && order.equals("name_desc")){
            seller.getFollowers().sort(Comparator.comparing(User::getUserName).reversed());
        }
        return seller;
    }

    @Override
    public Shopper getSellersByShopper(int userId, String order) {
        Shopper shopper = repository.getSellersByShopperId(userId, order);
        if(shopper == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad request"
            );
        }
        return shopper;
    }
}

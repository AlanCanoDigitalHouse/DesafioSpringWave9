package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.exceptions.AlreadyFollowingException;
import com.desafiospring.socialmeli.exceptions.UserDoesNotExistException;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.models.Buyer;
import com.desafiospring.socialmeli.models.Seller;
import com.desafiospring.socialmeli.models.User;
import com.desafiospring.socialmeli.repositories.BuyerRepository;
import com.desafiospring.socialmeli.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUser {

    BuyerRepository buyerRepository;
    SellerRepository sellerRepository;

    public UserService(BuyerRepository buyerRepository, SellerRepository sellerRepository){
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void addFollower(int userId, int userIdToFollow) throws UserException {
        Buyer buyer = validateUser(userId);
        Seller seller = validateSeller(userIdToFollow);
        List<User> followed = buyer.getFollowed();
        List<User> followers = seller.getFollowers();
        if (followed.stream().anyMatch(user -> user.getUserId() == userIdToFollow)){
            throw new AlreadyFollowingException();
        }
        followed.add(seller);
        followers.add(buyer);
    }



    private Seller validateSeller(int userIdToFollow) throws UserException {
        Seller seller = this.sellerRepository.get(userIdToFollow);
        if (seller == null) {
            throw new UserDoesNotExistException(userIdToFollow, true);
        }
        return seller;
    }

    private Buyer validateUser(int userId) throws UserException {
        Buyer buyer = this.buyerRepository.get(userId);
        if (buyer == null) {
            throw new UserDoesNotExistException(userId, false);
        }
        return buyer;
    }
}

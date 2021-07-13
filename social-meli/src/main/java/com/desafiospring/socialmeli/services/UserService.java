package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.exceptions.UserDoesNotExistException;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.models.Buyer;
import com.desafiospring.socialmeli.models.Seller;
import com.desafiospring.socialmeli.repositories.BuyerRepository;
import com.desafiospring.socialmeli.repositories.SellerRepository;
import org.springframework.stereotype.Service;

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

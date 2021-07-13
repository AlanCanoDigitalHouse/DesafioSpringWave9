package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.responses.BuyerFollowedDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersCountDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersDTO;
import com.desafiospring.socialmeli.exceptions.AlreadyFollowingException;
import com.desafiospring.socialmeli.exceptions.UserDoesNotExistException;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.dtos.models.Buyer;
import com.desafiospring.socialmeli.dtos.models.Seller;
import com.desafiospring.socialmeli.dtos.models.User;
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
        followed.add(new User(seller.getUserId(), seller.getUserName()));
        followers.add(new User(buyer.getUserId(), buyer.getUserName()));
    }

    @Override
    public SellerFollowersCountDTO getFollowersCount(int userId) throws UserException {
        Seller seller = validateSeller(userId);
        SellerFollowersCountDTO sellerFollowersCountDTO = new SellerFollowersCountDTO(
                seller.getUserId(), seller.getUserName(), seller.getFollowers().size());
        return sellerFollowersCountDTO;
    }

    @Override
    public SellerFollowersDTO getFollowers(int userId) throws UserException {
        Seller seller = validateSeller(userId);
        SellerFollowersDTO sellerFollowersDTO = new SellerFollowersDTO(
                seller.getUserId(), seller.getUserName(), seller.getFollowers()
        );
        return sellerFollowersDTO;
    }

    @Override
    public BuyerFollowedDTO getFollowed(int userId) throws UserException {
        Buyer buyer = validateUser(userId);
        BuyerFollowedDTO buyerFollowedDTO = new BuyerFollowedDTO(
                buyer.getUserId(), buyer.getUserName(), buyer.getFollowed()
        );
        return buyerFollowedDTO;
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

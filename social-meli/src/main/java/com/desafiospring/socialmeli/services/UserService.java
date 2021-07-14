package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.responses.BuyerFollowedDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersCountDTO;
import com.desafiospring.socialmeli.dtos.responses.SellerFollowersDTO;
import com.desafiospring.socialmeli.exceptions.AlreadyFollowingException;
import com.desafiospring.socialmeli.exceptions.NotFollowingException;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.dtos.models.Buyer;
import com.desafiospring.socialmeli.dtos.models.Seller;
import com.desafiospring.socialmeli.dtos.models.User;
import com.desafiospring.socialmeli.handlers.ValidationHandler;
import com.desafiospring.socialmeli.repositories.BuyerRepository;
import com.desafiospring.socialmeli.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUser {

    private final String[] orderOptions = {"name_asc", "name_desc"};

    BuyerRepository buyerRepository;
    SellerRepository sellerRepository;

    public UserService(BuyerRepository buyerRepository, SellerRepository sellerRepository){
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void addFollower(int userId, int userIdToFollow) throws UserException {
        Buyer buyer = ValidationHandler.validateUser(userId, buyerRepository);
        Seller seller = ValidationHandler.validateSeller(userIdToFollow, sellerRepository);
        List<User> followed = buyer.getFollowed();
        List<User> followers = seller.getFollowers();
        if (followed.stream().anyMatch(user -> userIdToFollow == user.getUserId())){
            throw new AlreadyFollowingException();
        }
        followed.add(new User(seller.getUserId(), seller.getUserName()));
        followers.add(new User(buyer.getUserId(), buyer.getUserName()));
    }

    @Override
    public SellerFollowersCountDTO getFollowersCount(int userId) throws UserException {
        Seller seller = ValidationHandler.validateSeller(userId, sellerRepository);
        SellerFollowersCountDTO sellerFollowersCountDTO = new SellerFollowersCountDTO(
                seller.getUserId(), seller.getUserName(), seller.getFollowers().size());
        return sellerFollowersCountDTO;
    }

    @Override
    public SellerFollowersDTO getFollowers(int userId, String order) throws UserException {
        Seller seller = ValidationHandler.validateSeller(userId, sellerRepository);
        List<User> followers = getSortedUsers(seller.getFollowers(), order);

        SellerFollowersDTO sellerFollowersDTO = new SellerFollowersDTO(
                seller.getUserId(), seller.getUserName(), followers
        );
        return sellerFollowersDTO;
    }

    @Override
    public BuyerFollowedDTO getFollowed(int userId, String order) throws UserException {
        Buyer buyer = ValidationHandler.validateUser(userId, buyerRepository);
        List<User> followed = getSortedUsers(buyer.getFollowed(), order);

        BuyerFollowedDTO buyerFollowedDTO = new BuyerFollowedDTO(
                buyer.getUserId(), buyer.getUserName(), followed
        );
        return buyerFollowedDTO;
    }


    @Override
    public void removeFollower(int userId, int userIdToUnfollow) throws UserException {
        Buyer buyer = ValidationHandler.validateUser(userId, buyerRepository);
        Seller seller = ValidationHandler.validateSeller(userIdToUnfollow, sellerRepository);

        List<User> followed = buyer.getFollowed();
        List<User> followers = seller.getFollowers();
        if (!followed.stream().anyMatch(user -> userIdToUnfollow == user.getUserId())){
            throw new NotFollowingException();
        }

        followed.removeIf(user -> userIdToUnfollow == user.getUserId());
        followers.removeIf(user -> userId == user.getUserId());
    }

    /****** Service utils ******/

    private List<User> getSortedUsers(List<User> users, String order) {
        /** Validar orden*/
        if(Arrays.stream(orderOptions).noneMatch(order::equals)){
            return users;
        }

        /** Ordenar segun criterio */
        List<User> sortedList = users.stream()
                .sorted(userComparator(order)).collect(Collectors.toList());
        return sortedList;
    }

    protected Comparator<User> userComparator(String order) {
        Comparator<User> c = null;
        switch (order) {
            case "name_asc":
                c = User::compareTo;
                break;
            case "name_desc":
                c = Comparator.reverseOrder();
                break;
        }
        return c;
    }


}

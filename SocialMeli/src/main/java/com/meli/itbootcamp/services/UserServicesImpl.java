package com.meli.itbootcamp.services;

import com.meli.itbootcamp.dto.ResponseDTO;
import com.meli.itbootcamp.dto.UserCountDTO;
import com.meli.itbootcamp.dto.UserDTO;
import com.meli.itbootcamp.dto.UserFollowsDTO;
import com.meli.itbootcamp.exceptions.UserException;
import com.meli.itbootcamp.model.User;
import com.meli.itbootcamp.model.UserNonSeller;
import com.meli.itbootcamp.model.UserSeller;
import com.meli.itbootcamp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {


    private final UserRepository userRepository;

    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDTO followSeller(Integer nonSeller, Integer seller) throws UserException {
        UserNonSeller userNonSeller = userRepository.findUserNonSellerById(nonSeller);
        UserSeller userSeller = userRepository.findUserSellerById(seller);
        if(Objects.isNull(userNonSeller)){
            throw  new UserException(UserException.NOT_USER);
        }
        if(Objects.isNull(userSeller)){
            throw  new UserException(UserException.NOT_SELLER);
        }
        if (userNonSeller.getUserID().equals(userSeller.getUserID())) {
            throw new UserException(UserException.SAME_USER);
        }
        if (userNonSeller.followSeller(userSeller)) {
            return new ResponseDTO(200, "You're following a new Seller");
        } else {
            return new ResponseDTO(200, "Alredy following a the Seller");
        }
    }

    @Override
    public UserCountDTO numberFollowers(Integer seller) throws UserException {
        UserSeller userSeller = userRepository.findUserSellerById(seller);
        if (Objects.isNull(userSeller)) {
            throw new UserException(UserException.NOT_SELLER);
        }
        return new UserCountDTO(userSeller, userSeller.quantFollowers());

    }

    @Override
    public UserFollowsDTO followersList(Integer seller, Optional<String> orderByOptional) throws UserException {
        String orderBy;
        if(orderByOptional.isPresent()){
            orderBy= orderByOptional.get();
        }else {
            orderBy ="name_des";
        }
        UserSeller userSeller = userRepository.findUserSellerById(seller);

        if (Objects.isNull(userSeller)) {
            throw new UserException(UserException.NOT_SELLER);
        }
        List<User> followers=new ArrayList<>();
        if (orderBy.equalsIgnoreCase("name_asc") || orderBy.equalsIgnoreCase("name_des")) {
            if (orderBy.equalsIgnoreCase("name_asc")) {
                followers =
                        userSeller.listFollowers().stream().sorted(Comparator.comparing(user -> user.getUserName().toUpperCase())).collect(Collectors.toList());
            } else {
                followers =
                        userSeller.listFollowers().stream().sorted(Comparator.comparing(User::getUserName).reversed()).collect(Collectors.toList());
            }
        }
        return new UserFollowsDTO(userSeller, followers.stream().map(UserDTO::new).collect(Collectors.toList()));

    }

    @Override
    public UserFollowsDTO followedList(Integer nonSeller, Optional<String> orderByOptional) throws UserException {
        String orderBy;
        if(orderByOptional.isPresent()){
            orderBy= orderByOptional.get();
        }else {
            orderBy ="name_des";
        }
        UserNonSeller userNonSeller = userRepository.findUserNonSellerById(nonSeller);
        if (Objects.isNull(userNonSeller)) {
            throw new UserException(UserException.NOT_SELLER);
        }
        List<User> followers=new ArrayList<>();
        //TODO check same as US003
        if (orderBy.equalsIgnoreCase("name_asc") || orderBy.equalsIgnoreCase("name_des")) {
            if (orderBy.equalsIgnoreCase("name_asc")) {
                followers =
                        userNonSeller.sellersFollowed().stream().sorted(Comparator.comparing(User::getUserName)).collect(Collectors.toList());
            } else {
                followers =
                        userNonSeller.sellersFollowed().stream().sorted(Comparator.comparing(User::getUserName).reversed()).collect(Collectors.toList());
            }
        }
        return new UserFollowsDTO(userNonSeller, followers.stream().map(UserDTO::new).collect(Collectors.toList()));

    }

    @Override
    public ResponseDTO unfollowSeller(Integer nonSeller, Integer seller) throws UserException {
        UserNonSeller userNonSeller = userRepository.findUserNonSellerById(nonSeller);
        UserSeller userSeller = userRepository.findUserSellerById(seller);
        if(Objects.isNull(userNonSeller)){
            throw  new UserException(UserException.NOT_USER);
        }
        if(Objects.isNull(userSeller)){
            throw  new UserException(UserException.NOT_SELLER);
        }
        if (userNonSeller.getUserID().equals(userSeller.getUserID())) {
            throw new UserException(UserException.SAME_USER);
        }
        if (userNonSeller.unfollowSeller(userSeller)) {
            return new ResponseDTO(200, "You have stop following the Seller");
        } else {
            return new ResponseDTO(200, "You're not following the Seller");
        }

    }

    @Override
    public UserSeller findUserSellerById(Integer seller){
        return  userRepository.findUserSellerById(seller);
    }

    @Override
    public UserNonSeller findUserNonSellerById(Integer nonSeller) { return  userRepository.findUserNonSellerById(nonSeller);
    }


}

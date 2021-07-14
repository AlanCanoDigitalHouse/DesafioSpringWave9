package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.UserSellerResponseDTO;
import com.example.desafio1springboot.exceptions.*;
import com.example.desafio1springboot.repositories.IUserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService{
    IUserRepository iUserRepository;
    public UserServiceImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public void addNewFollowerToSellerUser(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException {
        iUserRepository.addFollower(userId, userIdToFollow);
    }

    @Override
    public UserSellerResponseDTO countFollowersForUser_(Integer userId) throws UserSellerNotFoundExceptions {
        var userSeller = iUserRepository.getUserSellerById(userId);
        return new UserSellerResponseDTO(userSeller.getUserId(), userSeller.getUserName(), userSeller.getFollowers().size());
    }

    @Override
    public UserSellerDTO followersList(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        if(!order.equals("name_asc") && !order.equals("name_desc"))
            throw new OrderNotValidException();
        if(order.equals("name_desc")) {
            iUserRepository.getUserSellerById(userId).getFollowers().sort((a , b) -> b.getUserName().compareTo(a.getUserName()));
        }  else {
            iUserRepository.getUserSellerById(userId).getFollowers().sort((a , b) -> a.getUserName().compareTo(b.getUserName()));
        }
        return iUserRepository.getUserSellerById(userId);
    }

    @Override
    public UserClientDTO followedListByClient_(Integer userId) throws UserClientDoesNotExistsException {
        return iUserRepository.followedListOfClient_(userId);
    }

    @Override
    public void unfollowSeller_By_(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserClientNotFollowingSellerException {
        iUserRepository.unfollowUser_(userId, userIdToFollow);
    }
}

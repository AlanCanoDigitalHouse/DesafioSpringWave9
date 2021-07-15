package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.dtos.responseDTO.UserSellerResponseDTO;
import com.example.desafio1springboot.exceptions.*;
import com.example.desafio1springboot.handlers.ServiceHandler;
import com.example.desafio1springboot.repositories.IUserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService{
    IUserRepository iUserRepository;
    public UserServiceImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public void newFollowClient_ForSeller_(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException {
        iUserRepository.addClient_FollowerToUserSeller_(userId, userIdToFollow);
    }

    @Override
    public UserSellerResponseDTO countFollowersForUserSeller_(Integer userId) throws UserSellerNotFoundExceptions {
        var userSeller = iUserRepository.getUserSellerByIdAndCheckIfExists(userId);
        return new UserSellerResponseDTO(userSeller.getUserId(), userSeller.getUserName(), userSeller.getFollowers().size());
    }

    @Override
    public UserSellerDTO followersClientListBySeller_(Integer userId, String order) throws UserSellerNotFoundExceptions, OrderNotValidException {
        ServiceHandler.checkIfOrder_Validation(order, "name_asc", "name_desc");

        iUserRepository.getUserSellerByIdAndCheckIfExists(userId).getFollowers().sort((a , b) -> a.getUserName().compareTo(b.getUserName()));

        if(order.equals("name_desc"))
            iUserRepository.getUserSellerByIdAndCheckIfExists(userId).getFollowers().sort((a , b) -> b.getUserName().compareTo(a.getUserName()));

        return iUserRepository.getUserSellerByIdAndCheckIfExists(userId);
    }

    @Override
    public UserClientDTO followedSellersListByClient_(Integer userId) throws UserClientDoesNotExistsException {
        return iUserRepository.followedListOfClient_(userId);
    }

    @Override
    public void unfollowSeller(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserClientNotFollowingSellerException {
        iUserRepository.unfollowByClient_TheUserSeller_(userId, userIdToFollow);
    }
}

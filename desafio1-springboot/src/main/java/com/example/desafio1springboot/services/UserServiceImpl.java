package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.UserClientDTO;
import com.example.desafio1springboot.dtos.UserSellerDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserSellerResponseDTO;
import com.example.desafio1springboot.exceptions.UserAlreadyFollowingSellerException;
import com.example.desafio1springboot.exceptions.UserClientDoesNotExistsException;
import com.example.desafio1springboot.exceptions.UserSellerNotFoundExceptions;
import com.example.desafio1springboot.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;


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
    public UserSellerDTO followersList(Integer userId) throws UserSellerNotFoundExceptions {
        return iUserRepository.getUserSellerById(userId);
    }

    @Override
    public UserClientDTO followedListByClient_(Integer userId) throws UserClientDoesNotExistsException {
        // todo: agregar el throw UserSellerNotFoundExceptions
        return iUserRepository.followedListOfClient_(userId);
    }
}

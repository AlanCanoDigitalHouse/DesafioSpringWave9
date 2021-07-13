package com.example.desafio1springboot.services;

import com.example.desafio1springboot.dtos.UserSellerDTO;
import com.example.desafio1springboot.dtos.responseDTO.UserResponseDTO;
import com.example.desafio1springboot.exceptions.UserSellerNotFoundExceptions;
import com.example.desafio1springboot.repositories.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService{
    IUserRepository iUserRepository;
    public UserServiceImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public ResponseEntity<String> followSeller(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions {
        if(iUserRepository.getUserSeller(userId, userIdToFollow) != null)
            return new ResponseEntity<>("todo OK", HttpStatus.OK);
        throw new UserSellerNotFoundExceptions();
    }

    @Override
    public UserResponseDTO countFollowersForUser_(Integer userId) throws UserSellerNotFoundExceptions {
        var userSeller = iUserRepository.foundSeller(userId);
        if(userSeller != null)
            return new UserResponseDTO(userSeller.getUserId(), userSeller.getUserName(), userSeller.getFollowers().size());
        throw new UserSellerNotFoundExceptions();
    }

}

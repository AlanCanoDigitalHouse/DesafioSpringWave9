package com.example.prueba.services;

import com.example.prueba.dtos.UserSellerDTO;
import com.example.prueba.dtos.responseDTO.UserResponseDTO;
import com.example.prueba.exceptions.UserSellerNotFoundExceptions;
import com.example.prueba.repositories.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

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

    @Override
    public UserSellerDTO followersList(Integer userId) throws UserSellerNotFoundExceptions {
        if(iUserRepository.foundSeller(userId) != null)
            return iUserRepository.foundSeller(userId);
        throw new UserSellerNotFoundExceptions();
    }
}

package com.example.desafiospring.services;

import com.example.desafiospring.dtos.response.ClientResponseDto;
import com.example.desafiospring.dtos.response.SellerResponseDto;
import com.example.desafiospring.dtos.response.UserResponseDto;
import com.example.desafiospring.exceptions.LogicValidationException;
import com.example.desafiospring.exceptions.UserNotFoundException;
import com.example.desafiospring.repositories.UserRepository;
import com.example.desafiospring.utils.Factory;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
        userRepository.setGeneratedData(Factory.generateData());
    }

    /**
     * update the data from the seller and the client adding them to the given collection and update the
     * general collection
     *
     * @param userId
     * @param userIdToFollow
     * @return the client with the new followed
     * @throws LogicValidationException if the client is already following the Seller
     */
    public ClientResponseDto followSeller(Integer userId, Integer userIdToFollow) {
        var client = getClientById(userId);
        var seller = getSellerById(userIdToFollow);
        if (!isFollowing(client, seller)) {
            seller.addFollower(new UserResponseDto(client.getId(), client.getUserName()));
            client.addFollowed(new UserResponseDto(seller.getId(), seller.getUserName()));
            userRepository.update(seller);
            userRepository.update(client);
            return client;
        }else{
            throw new LogicValidationException("The client is already following the seller");
        }
    }

    public UserResponseDto getUserById(Integer id) {
        return userRepository.getById(id);
    }


    private boolean isFollowing(ClientResponseDto client, SellerResponseDto seller) {
        var checking = seller.getFollowers().stream()
                .anyMatch(userResponseDto -> userResponseDto.getId() == client.getId());
        return checking;
    }

    public SellerResponseDto getSellerById(Integer id) {
        var seller = userRepository.getAll().stream()
                .filter(userResponseDto -> userResponseDto.getId() == id && userResponseDto.getClass()
                        .getSimpleName().equals("SellerResponseDto"));
        var firstSeller = seller.findFirst();
        if (firstSeller.isPresent()) {
            return (SellerResponseDto) firstSeller.get();
        } else {
            throw new UserNotFoundException("The seller is not present in the collection as seller");
        }
    }

    public ClientResponseDto getClientById(Integer id) {
        var client = userRepository.getAll().stream()
                .filter(userResponseDto -> userResponseDto.getId() == id && userResponseDto.getClass()
                        .getSimpleName().equals("ClientResponseDto"));
        var firstClient = client.findFirst();
        if (firstClient.isPresent()) {
            return (ClientResponseDto) firstClient.get();
        } else {
            throw new UserNotFoundException("The client is not present in the collection as client");
        }
    }

    public void unFollowSeller(Integer userId,Integer userIdToUnfollow){
        var client = getClientById(userId);
        var seller = getSellerById(userIdToUnfollow);
        if (isFollowing(client, seller)) {
            seller.removeFollower(new UserResponseDto(client.getId(), client.getUserName()));
            client.removeFollowed(new UserResponseDto(seller.getId(), seller.getUserName()));
            userRepository.update(seller);
            userRepository.update(client);
        }else{
            throw new LogicValidationException("The client is not following this seller");
        }

    }

}

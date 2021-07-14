package com.example.desafiospring.services;

import com.example.desafiospring.dtos.response.ClientResponseDto;
import com.example.desafiospring.dtos.response.SellerResponseDto;
import com.example.desafiospring.dtos.response.UserResponseDto;
import com.example.desafiospring.exceptions.LogicValidationException;
import com.example.desafiospring.exceptions.UserNotFoundException;
import com.example.desafiospring.repositories.UserRepository;
import com.example.desafiospring.utils.Factory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserServices implements Sorter<UserResponseDto> {
    private UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
        userRepository.setGeneratedData(Factory.generateUsers());
    }

    /**
     * update the data from the seller and the client adding them to the given collection and update the
     * general collection
     *
     * @param userId         Integer, unique id from the client
     * @param userIdToFollow Integer, unique id from the seller
     * @throws LogicValidationException if the client is already following the Seller
     */
    public void followSeller(Integer userId, Integer userIdToFollow) {
        var client = getClientById(userId);
        var seller = getSellerById(userIdToFollow);
        if (!isFollowing(client, seller)) {
            seller.addFollower(new UserResponseDto(client.getId(), client.getUserName()));
            client.addFollowed(new UserResponseDto(seller.getId(), seller.getUserName()));
            userRepository.update(seller);
            userRepository.update(client);
        } else {
            throw new LogicValidationException("The client is already following the seller");
        }
    }

    public UserResponseDto getUserById(Integer id) {
        return userRepository.getById(id);
    }


    private boolean isFollowing(ClientResponseDto client, SellerResponseDto seller) {
        return seller.getFollowers().stream()
                .anyMatch(userResponseDto -> userResponseDto.getId() == client.getId());
    }

    public SellerResponseDto getSellerById(Integer id) {
        var seller = userRepository.getAll().stream()
                .filter(userResponseDto -> userResponseDto.getId() == id && userResponseDto.getClass()
                        .getSimpleName().equals("SellerResponseDto"));
        var firstSeller = seller.findFirst();
        if (firstSeller.isPresent()) {
            return (SellerResponseDto) firstSeller.get();
        } else {
            String error = "The seller is not present in the collection as seller, you should try with userId 71 - 99";
            throw new UserNotFoundException(error);
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
            String error = "The client is not present in the collection as client, you should try with userId 1 - 69";
            throw new UserNotFoundException(error);
        }
    }

    public void unFollowSeller(Integer userId, Integer userIdToUnfollow) {
        var client = getClientById(userId);
        var seller = getSellerById(userIdToUnfollow);
        if (isFollowing(client, seller)) {
            seller.removeFollower(new UserResponseDto(client.getId(), client.getUserName()));
            client.removeFollowed(new UserResponseDto(seller.getId(), seller.getUserName()));
            userRepository.update(seller);
            userRepository.update(client);
        } else {
            throw new LogicValidationException("The client is not following this seller");
        }
    }

    @Override
    public List<UserResponseDto> sortDesc(List<UserResponseDto> list) {
        list.sort(Comparator.comparing(UserResponseDto::getUserName).reversed());
        return list;
    }

    @Override
    public List<UserResponseDto> sortAsc(List<UserResponseDto> list) {
        list.sort(Comparator.comparing(UserResponseDto::getUserName));
        return list;
    }

    @Override
    public List<UserResponseDto> sorterWrapper(List<UserResponseDto> list, String param) {
        if (param == null) return list;
        if (param.contains("name_asc")) return sortAsc(list);
        if (param.contains("name_desc")) return sortDesc(list);
        return list;
    }

}

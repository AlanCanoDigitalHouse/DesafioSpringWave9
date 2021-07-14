package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.*;
import com.mercadolibre.socialmeli.dtos.resp.FollowedDTO;
import com.mercadolibre.socialmeli.dtos.resp.FollowersDTO;
import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerAlreadyFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;
import com.mercadolibre.socialmeli.repositories.interfaces.UserRepository;
import com.mercadolibre.socialmeli.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void follow(Integer userId, Integer userIdToFollow) throws BuyerNotFoundException, SellerNotFoundException, SellerAlreadyFollowedException {
        userRepository.addFollower(userIdToFollow, userId);
    }

    @Override
    public void unfollow(Integer userId, Integer userIdToUnfollow) throws SellerNotFoundException, SellerNotFollowedException, BuyerNotFoundException {
        userRepository.removeFollower(userIdToUnfollow, userId);
    }

    @Override
    public FollowersDTO getFollowersList(Integer userId, String order) throws SellerNotFoundException {
        SellerDTO seller = userRepository.getSellerById(userId);

        List<UserDTO> followers = new LinkedList<>(seller.getFollowers());
        sortUserList(followers, order);

        // build dto
        FollowersDTO dto = new FollowersDTO(seller);
        dto.setFollowers(followers);
        return dto;
    }

    @Override
    public FollowedDTO getFollowedList(Integer userId, String order) throws BuyerNotFoundException {
        BuyerDTO buyer = userRepository.getBuyerById(userId);

        List<UserDTO> followed = new LinkedList<>(buyer.getFollowed());
        sortUserList(followed, order);

        // build dto
        FollowedDTO dto = new FollowedDTO(buyer);
        dto.setFollowed(followed);
        return dto;
    }

    @Override
    public FollowersDTO getFollowersCount(Integer userId) throws SellerNotFoundException {
        SellerDTO seller = userRepository.getSellerById(userId);

        // build dto
        FollowersDTO dto = new FollowersDTO(seller);
        dto.setFollowers_count((long) seller.getFollowers().size());
        return dto;
    }

    @Override
    public SellerDTO getSellerById(Integer sellerId) throws SellerNotFoundException {
        return userRepository.getSellerById(sellerId);
    }

    @Override
    public BuyerDTO getBuyerById(Integer buyerId) throws BuyerNotFoundException {
        return userRepository.getBuyerById(buyerId);
    }

    private void sortUserList(List<UserDTO> userList, String order) {
        if(Objects.nonNull(order)) {
            userList.sort(Comparator.comparing(UserDTO::getUserName, String.CASE_INSENSITIVE_ORDER));
            if("name_desc".equals(order)){
                userList.sort(Comparator.comparing(UserDTO::getUserName, String.CASE_INSENSITIVE_ORDER).reversed());
            }
        }
    }
}

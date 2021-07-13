package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.*;
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
    public void follow(Integer userId, Integer userIdToFollow) {
        userRepository.addFollower(userIdToFollow, userId);
    }

    @Override
    public void unfollow(Integer userId, Integer userIdToUnfollow) {
        userRepository.removeFollower(userIdToUnfollow, userId);
    }

    @Override
    public FollowersListDTO getFollowersList(Integer userId, String order) {
        SellerDTO seller = userRepository.getSellerById(userId);

        List<UserDTO> followers = new LinkedList<>(seller.getFollowers());
        sortUserList(followers, order);

        FollowersListDTO dto = new FollowersListDTO();
        dto.setUserId(seller.getUserId());
        dto.setUserName(seller.getUserName());
        dto.setFollowers(followers);

        return dto;
    }

    @Override
    public FollowedListDTO getFollowedList(Integer userId, String order) {
        BuyerDTO buyer = userRepository.getBuyerById(userId);

        List<UserDTO> followed = new LinkedList<>(buyer.getFollowed());
        sortUserList(followed, order);

        FollowedListDTO dto = new FollowedListDTO();
        dto.setUserId(buyer.getUserId());
        dto.setUserName(buyer.getUserName());
        dto.setFollowed(followed);

        return dto;
    }

    @Override
    public FollowersCountDTO getFollowersCount(Integer userId) {
        SellerDTO seller = userRepository.getSellerById(userId);

        FollowersCountDTO dto = new FollowersCountDTO();
        dto.setUserId(seller.getUserId());
        dto.setUserName(seller.getUserName());
        dto.setFollowers_count((long) seller.getFollowers().size());

        return dto;
    }

    private void sortUserList(List<UserDTO> userList, String order) {
        if(Objects.nonNull(order)) {
            if("name_asc".equals(order)) {
                userList.sort(Comparator.comparing(UserDTO::getUserName));
            } else if("name_desc".equals(order)){
                userList.sort(Comparator.comparing(UserDTO::getUserName).reversed());
            }
        }
    }
}

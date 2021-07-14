package com.example.socialmeli.services.implementations;

import com.example.socialmeli.domains.User;
import com.example.socialmeli.dtos.response.*;
import com.example.socialmeli.exceptions.DataNotFound;
import com.example.socialmeli.exceptions.OrderInvalidFormatException;
import com.example.socialmeli.exceptions.SameUserException;
import com.example.socialmeli.handlers.ObjectMapper;
import com.example.socialmeli.repositories.interfaces.UserRepository;
import com.example.socialmeli.services.interfaces.UserServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;

    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO save(String userName) {
        User user = new User(userName);
        user = userRepository.saveUser(user);
        return new UserResponseDTO(user.getUserId(), user.getUserName());
    }

    @Override
    public List<UserResponseDTO> findAll() {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();
        users.forEach(user -> userResponseDTOS.add(new UserResponseDTO(user.getUserId(), user.getUserName())));
        return userResponseDTOS;
    }

    @Override
    public User findByUserId(Integer userId) throws DataNotFound {
        return userRepository.findByUserId(userId);
    }

    @Override
    public SuccessResponseDTO followUser(Integer userId, Integer userIdToFollow) throws DataNotFound, SameUserException {
        User user = userRepository.findByUserId(userId);
        User userToFollow = userRepository.findByUserId(userIdToFollow);
        if (user.getUserId().equals(userToFollow.getUserId())) {
            throw new SameUserException(SameUserException.ERROR);
        }
        SuccessResponseDTO successResponseDTO;
        if (!user.getFollowed().contains(userToFollow)) {
            user.getFollowed().add(userToFollow);
            userToFollow.getFollowers().add(user);
            successResponseDTO = new SuccessResponseDTO(200, "Follow SuccessFull");
        } else {
            successResponseDTO = new SuccessResponseDTO(200, "Already following");
        }
        return successResponseDTO;
    }

    @Override
    public UserFollowersDTO findFollowersByUser(String userId) throws DataNotFound {
        User user;
        try {
            user = userRepository.findByUserId(Integer.parseInt(userId));
        } catch (NumberFormatException exception) {
            user = userRepository.findByUserName(userId);
        }
        return ObjectMapper.toUserFollowersDTO(user);
    }

    @Override
    public UserFollowersDetailsDTO findFollowersDetailByUser(String userId) throws DataNotFound {
        User user;
        try {
            user = userRepository.findByUserId(Integer.parseInt(userId));
        } catch (NumberFormatException exception) {
            user = userRepository.findByUserName(userId);
        }
        return ObjectMapper.toUserFollowersDetail(user);
    }

    @Override
    public UserFollowersDetailsDTO findFollowersDetailByUser(Integer userId, String order)
            throws DataNotFound, OrderInvalidFormatException {
        if (order.equalsIgnoreCase("name_asc") || order.equalsIgnoreCase("name_desc")) {
            User user = userRepository.findByUserId(userId);
            List<User> followersSorted;
            if (order.equalsIgnoreCase("name_asc")) {
                followersSorted = user.getFollowers().stream().sorted(Comparator.comparing(User::getUserName))
                        .collect(Collectors.toList());
            } else {
                followersSorted = user.getFollowers().stream().sorted(Comparator.comparing(User::getUserName)
                        .reversed()).collect(Collectors.toList());
            }
            user.setFollowers(followersSorted);
            return ObjectMapper.toUserFollowersDetail(user);
        }
        throw new OrderInvalidFormatException(OrderInvalidFormatException.ERROR_NAME);
    }


    @Override
    public UserFollowedDetailsDTO findFollowedDetailByUser(String userId) throws DataNotFound {
        User user;
        try {
            user = userRepository.findByUserId(Integer.parseInt(userId));
        } catch (NumberFormatException e) {
            user = userRepository.findByUserName(userId);
        }
        return ObjectMapper.toUserFollowed(user);
    }

    @Override
    public UserFollowedDetailsDTO findFollowedDetailByUser(Integer userId, String order)
            throws DataNotFound, OrderInvalidFormatException {
        if (order.equalsIgnoreCase("name_asc") || order.equalsIgnoreCase("name_desc")) {
            User user = userRepository.findByUserId(userId);
            List<User> followedSorted;
            if (order.equalsIgnoreCase("name_asc")) {
                followedSorted = user.getFollowed().stream().sorted(Comparator.comparing(User::getUserName))
                        .collect(Collectors.toList());
            } else {
                followedSorted = user.getFollowed().stream().sorted(Comparator.comparing(User::getUserName)
                        .reversed()).collect(Collectors.toList());
            }
            user.setFollowed(followedSorted);
            return ObjectMapper.toUserFollowed(user);
        }
        throw new OrderInvalidFormatException(OrderInvalidFormatException.ERROR_NAME);
    }

    @Override
    public SuccessResponseDTO unFollowUser(Integer userId, Integer userIdToUnFollow) throws DataNotFound, SameUserException {
        User user = userRepository.findByUserId(userId);
        User userToUnFollow = userRepository.findByUserId(userIdToUnFollow);
        if (user.getUserId().equals(userToUnFollow.getUserId())) {
            throw new SameUserException(SameUserException.ERROR_UNFOLLOW);
        }
        SuccessResponseDTO successResponseDTO;
        if(user.getFollowed().contains(userToUnFollow)) {
            user.getFollowed().remove(userToUnFollow);
            userToUnFollow.getFollowers().remove(user);
            successResponseDTO = new SuccessResponseDTO(200, "UnFollow SuccessFull");
        }else{
            successResponseDTO = new SuccessResponseDTO(200, "Not following");
        }
        return successResponseDTO;
    }

}

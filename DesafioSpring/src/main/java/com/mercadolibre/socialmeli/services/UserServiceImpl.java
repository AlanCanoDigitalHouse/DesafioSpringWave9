package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.response.UserFollowedListResponseDTO;
import com.mercadolibre.socialmeli.dtos.response.UserFollowersCountResponseDTO;
import com.mercadolibre.socialmeli.dtos.response.UserFollowersListResponseDTO;
import com.mercadolibre.socialmeli.dtos.response.UserResponseDTO;
import com.mercadolibre.socialmeli.exceptions.AplicationException;
import com.mercadolibre.socialmeli.models.User;
import com.mercadolibre.socialmeli.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void init() {
        User user1 = new User(1, "Jorge");
        User user2 = new User(2, "Nacho");
        User user3 = new User(3, "Juli");
        User user4 = new User(4, "Tito");
        User user5 = new User(5, "Pepe");

        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
        userRepository.addUser(user4);
        userRepository.addUser(user5);
    }

    @Override
    public void follow(Integer userId, Integer userIdToFollow) {
        Optional<User> user = this.userRepository.findUserByUserId(userId);
        Optional<User> userToFollow = this.userRepository.findUserByUserId(userIdToFollow);

        if (user.isPresent() && userToFollow.isPresent()) {
            user.get().getFollowed().add(userToFollow.get());
            userToFollow.get().getFollowers().add(user.get());
        } else {
            throw new AplicationException("User not found", "Please check");
        }
    }

    @Override
    public void unFollow(Integer userId, Integer userIdToUnFollow) {
        Optional<User> user = this.userRepository.findUserByUserId(userId);
        Optional<User> userToUnFollow = this.userRepository.findUserByUserId(userIdToUnFollow);
        if (user.isPresent() && userToUnFollow.isPresent()) {
            user.get().getFollowed().remove(userToUnFollow.get());
            userToUnFollow.get().getFollowers().remove(user.get());
        } else {
            throw new AplicationException("User not found", "Please check");
        }
    }

    @Override
    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public UserFollowersCountResponseDTO followersCount(Integer userId) {
        Optional<User> user = this.userRepository.findUserByUserId(userId);
        UserFollowersCountResponseDTO responseDTO = new UserFollowersCountResponseDTO();
        if (user.isPresent()) {
            responseDTO.setUserID(user.get().getUserID());
            responseDTO.setUserName(user.get().getUserName());
            responseDTO.setFollowers_count(user.get().getFollowers().size());

            return responseDTO;

        } else {
            throw new AplicationException("User not found", "Please check");
        }
    }

    @Override
    public UserFollowersListResponseDTO followersList(Integer userId, String order) {

        Optional<User> user = this.userRepository.findUserByUserId(userId);
        UserFollowersListResponseDTO responseDTO = new UserFollowersListResponseDTO();
        List<UserResponseDTO> followersList = new ArrayList<>();
        if (user.isPresent()) {

            user.get().getFollowers().forEach(a -> followersList.add(new UserResponseDTO(a.getUserID(), a.getUserName())));

            if (order.equals("name_desc")) {
                Collections.sort(followersList, Collections.reverseOrder());
            } else if (order.equals("name_asc")) {
                Collections.sort(followersList);
            }

            responseDTO.setUserID(user.get().getUserID());
            responseDTO.setUserName(user.get().getUserName());
            responseDTO.setFollowers(followersList);

            return responseDTO;
        } else {
            throw new AplicationException("User not found", "Please check");
        }
    }

    @Override
    public UserFollowedListResponseDTO followedList(Integer userId, String order) {

        Optional<User> user = this.userRepository.findUserByUserId(userId);
        UserFollowedListResponseDTO responseDTO = new UserFollowedListResponseDTO();
        List<UserResponseDTO> followedList = new ArrayList<>();
        if (user.isPresent()) {

            user.get().getFollowed().forEach(a -> followedList.add(new UserResponseDTO(a.getUserID(), a.getUserName())));

            if (order.equals("name_desc")) {
                Collections.sort(followedList, Collections.reverseOrder());
            } else if (order.equals("name_asc")) {
                Collections.sort(followedList);
            }

            responseDTO.setUserID(user.get().getUserID());
            responseDTO.setUserName(user.get().getUserName());
            responseDTO.setFollowed(followedList);

            return responseDTO;
        } else {
            throw new AplicationException("User not found", "Please check");
        }

    }

    @Override
    public boolean userExist(Integer userId) {
        return Objects.nonNull(this.userRepository.findUserByUserId(userId));
    }

    @Override
    public Optional<User> findUserByUserId(Integer userId) {
        return this.userRepository.findUserByUserId(userId);
    }


}

package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.FollowedListDTO;
import com.jbianchini.meli.socialmeli.dto.FollowersCountDTO;
import com.jbianchini.meli.socialmeli.dto.FollowersListDTO;
import com.jbianchini.meli.socialmeli.dto.UserDTO;
import com.jbianchini.meli.socialmeli.dto.response.ResponseDTO;
import com.jbianchini.meli.socialmeli.dto.response.SuccessResponseDTO;
import com.jbianchini.meli.socialmeli.exception.ValidationException;
import com.jbianchini.meli.socialmeli.model.User;
import com.jbianchini.meli.socialmeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDTO createUser(UserDTO userDTO) {
        User user = this.create(userDTO);
        userDTO.setUserId(user.getUserId());

        return new SuccessResponseDTO("User created successfully", userDTO);
    }

    @Override
    public User findByUserId(Integer userId) {
        return this.userRepository.findByUserId(userId);
    }

    @Override
    public ResponseDTO follow(Integer userId, Integer userIdToFollow) {
        this.validateSameUser(userId, userIdToFollow);

        User user = this.userRepository.findByUserId(userId);
        User userToFollow = this.userRepository.findByUserId(userIdToFollow);

        ResponseDTO response =
                new SuccessResponseDTO("", new UserDTO(userToFollow.getUserId(), userToFollow.getUserName()));

        if (this.follows(user, userToFollow)) {
            //TODO: Take this to .properties
            response.setMessage("User already followed");
        } else {

            user.getFollowed().add(userToFollow);
            userToFollow.getFollowers().add(user);

            response.setMessage("User followed successfully");
        }

        return response;

    }


    @Override
    public FollowersCountDTO getFollowersCount(Integer userId) {
        User user = this.userRepository.findByUserId(userId);

        FollowersCountDTO response = new FollowersCountDTO();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setFollowers_count(user.getFollowers().size());
        return response;
    }

    @Override
    public FollowersListDTO getFollowers(Integer userID, String order) {
        User user = this.userRepository.findByUserId(userID);
        List<UserDTO> followersDTOList = new ArrayList<>();

        convertToDTO(followersDTOList, user.getFollowers());

        this.sortByName(followersDTOList, order);

        return new FollowersListDTO(user.getUserId(), user.getUserName(), followersDTOList);

    }

    @Override
    public FollowedListDTO getFollowed(Integer userID, String order) {
        User user = this.userRepository.findByUserId(userID);
        List<UserDTO> followedDTOList = new ArrayList<>();

        convertToDTO(followedDTOList, user.getFollowed());

        this.sortByName(followedDTOList, order);

        return new FollowedListDTO(user.getUserId(), user.getUserName(), followedDTOList);

    }

    @Override
    public ResponseDTO unFollow(Integer userId, Integer userIdToUnfollow) {
        this.validateSameUser(userId, userIdToUnfollow);

        User user = this.userRepository.findByUserId(userId);
        User userToUnFollow = this.userRepository.findByUserId(userIdToUnfollow);

        ResponseDTO response =
                new SuccessResponseDTO("", new UserDTO(userToUnFollow.getUserId(), userToUnFollow.getUserName()));

        if (this.follows(user, userToUnFollow)) {
            user.getFollowed().remove(userToUnFollow);
            userToUnFollow.getFollowers().remove(user);
            response.setMessage("User successfully unfollowed");
        } else {
            //TODO: Take this to .properties
            response.setMessage("User not followed yet");
        }

        return response;
    }

    private void convertToDTO(List<UserDTO> followedDTOList, List<User> followed) {
        followed.stream().forEach(u -> followedDTOList.add(new UserDTO(u.getUserId(), u.getUserName())));
    }

    private void sortByName(List<UserDTO> users, String order) {
        switch (order) {
            case "name_asc":
                Collections.sort(users, Comparator.comparing(UserDTO::getUserName));
                break;
            case "name_desc":
                Collections.sort(users, Comparator.comparing(UserDTO::getUserName).reversed());
                break;
            case "":
                break;
        }
    }

    private void validateSameUser(Integer userId, Integer userIdToFollow) {
        if (userId.equals(userIdToFollow)) {
            throw new ValidationException("An user cannot follow himself.");
        }
    }

    private boolean follows(User user, User userToFollow) {
        return user.getFollowed().contains(userToFollow);
    }

    private User create(UserDTO userDTO) {
        return this.userRepository.save(new User(userDTO.getUserName()));
    }

}

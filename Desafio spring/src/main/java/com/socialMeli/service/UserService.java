package com.socialMeli.service;

import com.socialMeli.dto.response.*;
import com.socialMeli.exception.exception.AlreadyFollowedException;
import com.socialMeli.exception.exception.FollowHimselfException;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.model.UserModel;
import com.socialMeli.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("userService")
public class UserService{
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * A user can follow other user
     */
    public FollowResultResponseDTO follow(int userId, int userIdToFollow) throws ModelNotExists, FollowHimselfException, AlreadyFollowedException {
        UserModel userModel = userRepository.findById(userId);
        UserModel toFollow = userRepository.findById(userIdToFollow);
        if (userModel.getId() == toFollow.getId()) throw new FollowHimselfException(toFollow.getUserName());
        if (userModel.getFollowed().contains(toFollow.getId()))
            throw new AlreadyFollowedException(userModel.getUserName(), toFollow.getUserName());
        userModel.addNewUserFollowed(toFollow.getId());
        userRepository.modify(userModel);
        return new FollowResultResponseDTO(userModel.getUserName(), userModel.getId(), toFollow.getUserName(), toFollow.getId());
    }

    @SuppressWarnings("SpellCheckingInspection")
    public CountFollowersResponseDTO getCountOfFollowers(int idUser) throws ModelNotExists {
        //Used for esthetic of DTO
        UserModel userObjective = userRepository.findById(idUser);

        List<UserModel> users = userRepository.findAll();
        int followers = (int) users.stream().filter(user -> user.getFollowed().contains(idUser)).count();
        return new CountFollowersResponseDTO(userObjective.getId(), userObjective.getUserName(), followers);
    }

    public UserFollowersResponseDTO getListFollowers(int idUser) throws ModelNotExists {
        UserModel userObjective = userRepository.findById(idUser);
        List<UserModel> users = userRepository.findAll();
        List<BasicUserResponseDTO> followers = users.stream()
                .filter(user -> user.getFollowed().contains(idUser))
                .map(follower -> new BasicUserResponseDTO(follower.getId(), follower.getUserName()))
                .collect(Collectors.toList());
        return new UserFollowersResponseDTO(idUser, userObjective.getUserName(), followers);
    }

    public UserFollowedResponseDTO getListUsersFollowed(int idUser) throws ModelNotExists {
        UserModel userModel = userRepository.findById(idUser);
        List<BasicUserResponseDTO> followed = new ArrayList<>();
        for (Integer id : userModel.getFollowed()) {
            UserModel actual = userRepository.findById(id);
            followed.add(new BasicUserResponseDTO(actual.getId(), actual.getUserName()));
        }
        return new UserFollowedResponseDTO(userModel.getId(), userModel.getUserName(), followed);
    }
}

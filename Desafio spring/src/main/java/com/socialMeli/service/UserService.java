package com.socialMeli.service;

import com.socialMeli.dto.response.FollowResultResponseDTO;
import com.socialMeli.exception.exception.AlreadyFollowedException;
import com.socialMeli.exception.exception.FollowHimselfException;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.model.UserModel;
import com.socialMeli.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("userService")
public class UserService implements IService {
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
}

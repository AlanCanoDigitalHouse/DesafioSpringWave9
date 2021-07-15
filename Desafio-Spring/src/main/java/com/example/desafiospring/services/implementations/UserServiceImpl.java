package com.example.desafiospring.services.implementations;

import com.example.desafiospring.DTOS.requests.FollowUserRequestDTO;
import com.example.desafiospring.DTOS.requests.OnlyUserIDRequestDTO;
import com.example.desafiospring.DTOS.requests.UserIDAndOrderRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowUserResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowedListResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerCountResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerListResponseDTO;
import com.example.desafiospring.entities.UserEntity;
import com.example.desafiospring.repository.interfaces.UserRepository;
import com.example.desafiospring.services.interfaces.FollowService;
import com.example.desafiospring.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    FollowService followService;

    public UserServiceImpl(UserRepository userRepository, FollowService followService) {
        this.userRepository = userRepository;
        this.followService = followService;
    }

    @Override
    public FollowUserResponseDTO followUser(FollowUserRequestDTO followUserRequestDTO) {
        followUserValidations(followUserRequestDTO);
        followService.addNewFollow(followUserRequestDTO.getFollowerUserId(), followUserRequestDTO.getFollowedUserId());
        return new FollowUserResponseDTO("OK");
    }

    private void followUserValidations(FollowUserRequestDTO followUserRequestDTO) {
        Integer followerID = followUserRequestDTO.getFollowerUserId();
        Integer followedID = followUserRequestDTO.getFollowedUserId();
        validateUsersExistence(followerID, followedID);
        validateDifferentUsers(followerID, followedID);
    }

    private void validateDifferentUsers(Integer userId, Integer userIdToFollow) {
        if (userId.equals(userIdToFollow)) {
            throw new IllegalArgumentException("Users can't follow themselves. ID:" + userId);
        }
    }

    @Override
    public FollowerCountResponseDTO followerCount(OnlyUserIDRequestDTO onlyUserIDRequestDTO) {
        Integer userId = onlyUserIDRequestDTO.getUserId();
        validateUsersExistence(userId);
        return new FollowerCountResponseDTO(
                userId,
                getUserByID(userId).getUserName(),
                followService.getFollowerIDs(userId).size());
    }

    @Override
    public FollowerListResponseDTO followerList(UserIDAndOrderRequestDTO userIDAndOrderRequestDTO) {
        Integer userId = userIDAndOrderRequestDTO.getUserId();
        validateUsersExistence(userId);
        List<Integer> followerIDS = followService.getFollowerIDs(userId);
        return new FollowerListResponseDTO(
                userId,
                getUserByID(userId).getUserName(),
                userRepository.getUsersByID(followerIDS, userIDAndOrderRequestDTO.getOrder()));
    }

    @Override
    public FollowedListResponseDTO followedList(UserIDAndOrderRequestDTO userIDAndOrderRequestDTO) {
        Integer userId = userIDAndOrderRequestDTO.getUserId();
        validateUsersExistence(userId);
        List<Integer> followedIDS = followService.getFollowedIDs(userId);
        return new FollowedListResponseDTO(
                userId,
                getUserByID(userId).getUserName(),
                userRepository.getUsersByID(followedIDS, userIDAndOrderRequestDTO.getOrder()));
    }

    @Override
    public FollowUserResponseDTO unFollowUser(FollowUserRequestDTO followUserRequestDTO) {
        followUserValidations(followUserRequestDTO);
        followService.deleteFollow(followUserRequestDTO.getFollowerUserId(), followUserRequestDTO.getFollowedUserId());
        return new FollowUserResponseDTO("OK");
    }

    @Override
    public void validateUsersExistence(Integer... userIDS) {
        for (Integer id : userIDS) {
            userRepository.validateExistOrException(id);
        }
    }

    @Override
    public UserEntity getUserByID(Integer userId) {
        return userRepository.getUserByID(userId);
    }
}

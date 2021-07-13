package com.example.desafiospring.services.implementations;

import com.example.desafiospring.DTOS.requests.FollowUserRequestDTO;
import com.example.desafiospring.DTOS.requests.OnlyUserIDRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowUserResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowedListResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerCountResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerListResponseDTO;
import com.example.desafiospring.repository.interfaces.FollowRepository;
import com.example.desafiospring.repository.interfaces.UserRepository;
import com.example.desafiospring.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    FollowRepository followRepository;

    public UserServiceImpl(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    @Override
    public FollowUserResponseDTO followUser(FollowUserRequestDTO followUserRequestDTO) {
        followUserValidations(followUserRequestDTO);
        followRepository.addNewFollow(followUserRequestDTO.getFollowerUserId(), followUserRequestDTO.getFollowedUserId());
        return new FollowUserResponseDTO("OK");
    }

    private void followUserValidations(FollowUserRequestDTO followUserRequestDTO) {
        Integer followerID=followUserRequestDTO.getFollowerUserId();
        Integer followedID=followUserRequestDTO.getFollowedUserId();
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
        validateUsersExistence(onlyUserIDRequestDTO.getUserId());
        Integer userId = onlyUserIDRequestDTO.getUserId();
        return new FollowerCountResponseDTO(
                userId,
                userRepository.getUserByID(userId).getUserName(),
                followRepository.getFollowerIDs(userId).size());
    }

    @Override
    public FollowerListResponseDTO followerList(OnlyUserIDRequestDTO onlyUserIDRequestDTO) {
        validateUsersExistence(onlyUserIDRequestDTO.getUserId());
        Integer userId = onlyUserIDRequestDTO.getUserId();
        List<Integer> followerIDS = followRepository.getFollowerIDs(userId);
        return new FollowerListResponseDTO(
                userId,
                userRepository.getUserByID(userId).getUserName(),
                userRepository.getUsersByID(followerIDS));
    }

    @Override
    public FollowedListResponseDTO followedList(OnlyUserIDRequestDTO onlyUserIDRequestDTO) {
        validateUsersExistence(onlyUserIDRequestDTO.getUserId());
        Integer userId = onlyUserIDRequestDTO.getUserId();
        List<Integer> followedIDS = followRepository.getFollowedIDs(userId);
        return new FollowedListResponseDTO(
                userId,
                userRepository.getUserByID(userId).getUserName(),
                userRepository.getUsersByID(followedIDS));
    }

    @Override
    public FollowUserResponseDTO unFollowUser(FollowUserRequestDTO followUserRequestDTO) {
        followUserValidations(followUserRequestDTO);
        followRepository.deleteFollow(followUserRequestDTO.getFollowerUserId(), followUserRequestDTO.getFollowedUserId());
        return new FollowUserResponseDTO("OK");
    }

    private void validateUsersExistence(Integer... userIDS) {
        for (Integer id : userIDS) {
            userRepository.validateExistOrException(id);
        }
    }
}

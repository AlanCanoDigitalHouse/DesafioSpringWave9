package com.example.desafiospring.services.implementations;

import com.example.desafiospring.DTOS.requests.FollowUserRequestDTO;
import com.example.desafiospring.DTOS.requests.FollowerCountRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowUserResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerCountResponseDTO;
import com.example.desafiospring.repository.interfaces.FollowRepository;
import com.example.desafiospring.repository.interfaces.UserRepository;
import com.example.desafiospring.services.interfaces.UserService;
import org.springframework.stereotype.Service;

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
        FollowUserResponseDTO followUserResponseDTO = new FollowUserResponseDTO();
        validateUsersExistence(followUserRequestDTO.getUserId(),followUserRequestDTO.getUserIdToFollow());
        validateSameUser(followUserRequestDTO.getUserId(),followUserRequestDTO.getUserIdToFollow());
        followRepository.addNewFollow(followUserRequestDTO.getUserId(),followUserRequestDTO.getUserIdToFollow());
        followUserResponseDTO.setMessage("OK");
        return followUserResponseDTO;
    }

    private void validateSameUser(Integer userId, Integer userIdToFollow) {
        if (userId.equals(userIdToFollow)){
            throw new IllegalArgumentException("Users can't follow themselves. ID:"+userId);
        }
    }

    @Override
    public FollowerCountResponseDTO followerCount(FollowerCountRequestDTO followerCountRequestDTO) {
        FollowerCountResponseDTO followerCountResponseDTO = new FollowerCountResponseDTO();
        validateUsersExistence(followerCountRequestDTO.getUserId());
        fillFollowerCountResponseDTO(followerCountRequestDTO.getUserId(),followerCountResponseDTO);
        return followerCountResponseDTO;
    }

    private void fillFollowerCountResponseDTO(Integer userId, FollowerCountResponseDTO followerCountResponseDTO) {
        followerCountResponseDTO.setUserId(userId);
        followerCountResponseDTO.setUserName(userRepository.getUserByID(userId).getUserName());
        followerCountResponseDTO.setFollowers_count(followRepository.getFollowerIDs(userId).size());
    }

    private void validateUsersExistence(Integer... userIDS) {
        for (Integer id: userIDS) {
            userRepository.validateExistOrException(id);
        }
    }
}

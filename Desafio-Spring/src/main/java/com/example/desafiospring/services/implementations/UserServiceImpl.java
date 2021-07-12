package com.example.desafiospring.services.implementations;

import com.example.desafiospring.DTOS.requests.FollowUserRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowUserResponseDTO;
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
        followRepository.addNewFollow(followUserRequestDTO.getUserId(),followUserRequestDTO.getUserIdToFollow());
        followUserResponseDTO.setMessage("OK");
        return followUserResponseDTO;
    }

    private void validateUsersExistence(Integer... userIDS) {
        for (Integer id: userIDS) {
            userRepository.validateExistOrException(id);
        }
    }
}

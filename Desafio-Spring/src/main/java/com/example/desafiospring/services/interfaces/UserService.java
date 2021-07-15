package com.example.desafiospring.services.interfaces;

import com.example.desafiospring.DTOS.requests.FollowUserRequestDTO;
import com.example.desafiospring.DTOS.requests.OnlyUserIDRequestDTO;
import com.example.desafiospring.DTOS.requests.UserIDAndOrderRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowUserResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowedListResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerCountResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerListResponseDTO;
import com.example.desafiospring.entities.UserEntity;

public interface UserService {
    FollowUserResponseDTO followUser(FollowUserRequestDTO followUserRequestDTO);

    FollowerCountResponseDTO followerCount(OnlyUserIDRequestDTO onlyUserIDRequestDTO);

    FollowerListResponseDTO followerList(UserIDAndOrderRequestDTO userIDAndOrderRequestDTO);

    FollowedListResponseDTO followedList(UserIDAndOrderRequestDTO userIDAndOrderRequestDTO);

    FollowUserResponseDTO unFollowUser(FollowUserRequestDTO followUserRequestDTO);

    void validateUsersExistence(Integer... userIDS);

    UserEntity getUserByID(Integer userId);
}

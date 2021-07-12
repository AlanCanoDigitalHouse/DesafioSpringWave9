package com.example.desafiospring.services.interfaces;

import com.example.desafiospring.DTOS.requests.FollowUserRequestDTO;
import com.example.desafiospring.DTOS.requests.FollowerCountRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowUserResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerCountResponseDTO;

public interface UserService {
    FollowUserResponseDTO followUser(FollowUserRequestDTO followUserRequestDTO);

    FollowerCountResponseDTO followerCount(FollowerCountRequestDTO followerCountRequestDTO);
}

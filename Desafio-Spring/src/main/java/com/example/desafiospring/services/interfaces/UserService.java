package com.example.desafiospring.services.interfaces;

import com.example.desafiospring.DTOS.requests.FollowUserRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowUserResponseDTO;

public interface UserService {
    FollowUserResponseDTO followUser(FollowUserRequestDTO followUserRequestDTO);
}

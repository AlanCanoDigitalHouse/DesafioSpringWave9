package com.example.desafiospring.services.interfaces;

import com.example.desafiospring.DTOS.requests.FollowUserRequestDTO;
import com.example.desafiospring.DTOS.requests.OnlyUserIDRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowUserResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowedListResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerCountResponseDTO;
import com.example.desafiospring.DTOS.responses.FollowerListResponseDTO;

public interface UserService {
    FollowUserResponseDTO followUser(FollowUserRequestDTO followUserRequestDTO);

    FollowerCountResponseDTO followerCount(OnlyUserIDRequestDTO onlyUserIDRequestDTO);

    FollowerListResponseDTO followerList(OnlyUserIDRequestDTO onlyUserIDRequestDTO);

    FollowedListResponseDTO followedList(OnlyUserIDRequestDTO onlyUserIDRequestDTO);

    FollowUserResponseDTO unFollowUser(FollowUserRequestDTO followUserRequestDTO);
}

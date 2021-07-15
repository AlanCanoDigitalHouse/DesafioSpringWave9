package com.meli.desafiospring.DTOs.response;

import com.meli.desafiospring.models.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FollowedListResponseDTO {

    Integer userId;
    String userName;
    List<UserResponseDTO> followed;

    public FollowedListResponseDTO(Integer userId, String userName, List<User> followed) {
        this.userId = userId;
        this.userName = userName;
        List<UserResponseDTO> userResponseDTOS;
        this.followed = followed
                .stream()
                .map(u -> new UserResponseDTO(u.getUserId(), u.getUserName()))
                .collect(Collectors.toList());
    }
}

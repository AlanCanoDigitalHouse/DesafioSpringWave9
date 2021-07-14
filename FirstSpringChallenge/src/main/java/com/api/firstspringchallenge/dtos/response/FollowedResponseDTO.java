package com.api.firstspringchallenge.dtos.response;

import com.api.firstspringchallenge.dtos.request.UserDTO;
import com.api.firstspringchallenge.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class FollowedResponseDTO {
    private int userId;
    private String username;
    private List<UserDTO> followed;

    public FollowedResponseDTO(User user, List<User> followed) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.followed = followed.stream().map(f -> new UserDTO(f.getUserId(), f.getUsername())).collect(Collectors.toList());
    }
}

package com.socialmeli.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.socialmeli.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FollowedResponseDTO {

    private Integer userId;
    private String userName;

    @JsonIgnoreProperties(value = { "posts", "followers", "followed" })
    private List<UserDTO> followed;
}

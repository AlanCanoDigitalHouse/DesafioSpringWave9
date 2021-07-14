package com.example.socialmeli.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowedDetailsDTO {

    private Integer userId;
    private String userName;
    private List<UserResponseDTO> followed;
}

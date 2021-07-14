package com.example.desafiospring.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowersListDTO {

    private Integer userId;
    private String userName;
    private List<UserDTO> followers;

}

package com.meli.desafiospring.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SimpleUserDTO {

    Integer userId;
    String userName;
    List<UserResponseDTO> followers;
    List<UserResponseDTO> followed;

}

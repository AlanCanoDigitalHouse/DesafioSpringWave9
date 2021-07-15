package com.example.demo.Services.Mapper;

import com.example.demo.DTOs.UserDTO;
import com.example.demo.Models.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static List<UserDTO> toDTO(List<UserModel> users){
        return users.stream().map(u-> new UserDTO(u.getUserId(), u.getUserName())).collect(Collectors.toList());
    }

}

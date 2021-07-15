package com.api.firstspringchallenge.dtos.response;

import com.api.firstspringchallenge.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UsersResponseDTO {
    private List<User> users;
}

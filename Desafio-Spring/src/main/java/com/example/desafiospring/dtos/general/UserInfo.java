package com.example.desafiospring.dtos.general;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.desafiospring.dtos.general.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String userName;
    private List<User> follower;
    private List<User> followed;
}

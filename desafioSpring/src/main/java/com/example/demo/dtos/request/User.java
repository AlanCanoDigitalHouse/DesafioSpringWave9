package com.example.demo.dtos.request;

import com.example.demo.dtos.response.UserDto;
import lombok.*;

import java.util.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;
    private String name;
    private List<UserDto> followers = new ArrayList<>();
    private List<UserDto> following = new ArrayList<>();

}

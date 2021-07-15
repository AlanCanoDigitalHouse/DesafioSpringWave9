package com.example.demo.dtos.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
public class FollowersList {

    private Integer userId;
    private String name;
    private List<UserDto> followers;

}

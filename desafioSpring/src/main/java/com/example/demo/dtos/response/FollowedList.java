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
public class FollowedList {

    private Integer userId;
    private String userName;
    private List<UserDto> followed;

}

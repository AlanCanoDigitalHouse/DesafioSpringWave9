package com.example.socialmeli.dtos.responses;

import com.example.socialmeli.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllUserResponseDto {
    private List<UserDto> users;
    private List<UserDto> sellers;
}

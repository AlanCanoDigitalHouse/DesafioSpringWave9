package com.example.socialmeli.dtos.responses;

import com.example.socialmeli.dtos.FollowDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllFollowersResponseDto {
    private Integer userId;
    private String userName;
    private List<FollowDto> followers;
}

package com.example.desafiospring.DTOS.responses;

import com.example.desafiospring.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FollowedListResponseDTO {
    private Integer userId;
    private String userName;
    private List<UserEntity> followed;
}

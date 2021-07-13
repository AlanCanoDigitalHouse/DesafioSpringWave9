package com.example.desafiospring.DTOS.responses;

import com.example.desafiospring.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class FollowerListResponseDTO {
    private Integer userId;
    private String userName;
    private List<UserEntity> followers;
}

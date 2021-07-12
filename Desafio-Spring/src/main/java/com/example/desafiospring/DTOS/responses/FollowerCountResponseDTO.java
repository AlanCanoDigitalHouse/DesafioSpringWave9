package com.example.desafiospring.DTOS.responses;

import lombok.Data;

@Data
public class FollowerCountResponseDTO {
    private Integer userId;
    private String userName;
    private Integer followers_count;
}

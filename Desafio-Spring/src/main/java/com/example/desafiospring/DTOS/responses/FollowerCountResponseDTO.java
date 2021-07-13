package com.example.desafiospring.DTOS.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowerCountResponseDTO {
    private Integer userId;
    private String userName;
    private Integer followers_count;
}

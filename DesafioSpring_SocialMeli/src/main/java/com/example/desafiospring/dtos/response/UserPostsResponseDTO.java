package com.example.desafiospring.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPostsResponseDTO {

    private int userId;
    private String userName;
    private int promoproductos_count;
}

package com.example.desafiospring.dtos.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO {

    int userId;
    String userName;
    int followers_count;
}

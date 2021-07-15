package com.desafio.spring.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class UserDto {
    private Integer userId;
    private String userName;
}

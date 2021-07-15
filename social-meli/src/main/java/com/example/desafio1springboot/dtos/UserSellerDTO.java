package com.example.desafio1springboot.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSellerDTO extends UserDTO{

    private List<UserDTO> followers;

}

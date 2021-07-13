package com.example.prueba.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserSellerDTO extends UserDTO{
    private List<UserDTO> followers;  // Todos los usuarios que siguen a este vendedor

}

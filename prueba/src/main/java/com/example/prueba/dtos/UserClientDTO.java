package com.example.prueba.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserClientDTO extends UserDTO{
    private List<UserDTO> followed;  // Sigue a sus vendedores favoritos
}
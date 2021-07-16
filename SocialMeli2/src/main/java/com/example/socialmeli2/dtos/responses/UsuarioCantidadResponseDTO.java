package com.example.socialmeli2.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioCantidadResponseDTO {
    private Integer userId;
    private String userName;
    private Integer followers_count;
}

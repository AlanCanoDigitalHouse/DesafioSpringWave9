package com.example.desafiospring.DTOS.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewPostResponseDTO {
    Integer productId;
    Integer postId;
}
package com.example.desafio1springboot.dtos.responseDTO;

import com.example.desafio1springboot.dtos.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponseDTO {
    private Integer id_post;
    private Date date;
    private ProductDTO detail;
    private Integer category;
    private double price;
}

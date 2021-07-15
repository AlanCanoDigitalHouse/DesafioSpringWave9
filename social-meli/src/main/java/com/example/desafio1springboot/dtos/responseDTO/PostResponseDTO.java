package com.example.desafio1springboot.dtos.responseDTO;

import com.example.desafio1springboot.dtos.ProductDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponseDTO {

    private Integer id_post;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    private ProductDTO detail;

    private Integer category;

    private Double price;

}

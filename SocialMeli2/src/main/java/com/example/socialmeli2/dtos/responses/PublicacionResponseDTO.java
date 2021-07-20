package com.example.socialmeli2.dtos.responses;

import lombok.Data;

public class PublicacionResponseDTO {

    private  Integer id_post;
    private String date;
    private ProductoResponseDTO detail;
    private  Integer category;
    private  double price;

}

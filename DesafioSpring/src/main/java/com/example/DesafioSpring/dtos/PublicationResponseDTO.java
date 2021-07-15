package com.example.DesafioSpring.dtos;

import com.example.DesafioSpring.models.Product;
import com.example.DesafioSpring.models.Publication;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data

public class PublicationResponseDTO {

    private Integer userId;
    private List<Publication> posts;

}

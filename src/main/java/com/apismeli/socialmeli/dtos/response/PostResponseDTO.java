package com.apismeli.socialmeli.dtos.response;

import com.apismeli.socialmeli.dtos.request.ProductDTO;
import com.apismeli.socialmeli.dtos.request.PublicationDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;


@Data
public class PostResponseDTO {
    private int id_post;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    private ProductDTO detail;
    private Integer category;
    private Double price;


    public PostResponseDTO(PublicationDTO publicationDTO) {
        this.id_post = publicationDTO.getId_post();
        this.date = publicationDTO.getDate();
        this.detail = publicationDTO.getDetail();
        this.category = publicationDTO.getCategory();
        this.price = publicationDTO.getPrice();
    }
}

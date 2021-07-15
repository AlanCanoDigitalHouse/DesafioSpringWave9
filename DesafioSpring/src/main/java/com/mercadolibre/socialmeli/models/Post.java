package com.mercadolibre.socialmeli.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.socialmeli.dtos.Product.request.PostDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Post {

    private Integer userId;
    private Integer id_post;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private Product detail;
    private Integer category;
    private Double price;

    public Post(Integer id, PostDTO postDTO) {
        this.userId = postDTO.getUserId();
        this.id_post = id;
        this.date = postDTO.getDate();
        this.detail = postDTO.getDetail();
        this.category = postDTO.getCategory();
        this.price = postDTO.getPrice();
    }
}

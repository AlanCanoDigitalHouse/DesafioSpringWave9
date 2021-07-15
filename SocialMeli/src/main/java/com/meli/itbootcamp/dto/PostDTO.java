package com.meli.itbootcamp.dto;

import com.meli.itbootcamp.model.Post;
import com.meli.itbootcamp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
public class PostDTO {
    private Integer id_post;
    private String date;
    private ProductDTO detail;
    private Integer category;
    private Double price;

    public PostDTO(Post post) {
        this.id_post = post.getId_post();
        this.date = new SimpleDateFormat("dd-MM-yyyy").format(post.getDate());
        this.detail = new ProductDTO(post.getItem());
        this.category = post.getCategory();
        this.price = post.getPrice();
    }
}


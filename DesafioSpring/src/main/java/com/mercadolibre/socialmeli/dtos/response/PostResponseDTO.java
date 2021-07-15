package com.mercadolibre.socialmeli.dtos.response;

import com.mercadolibre.socialmeli.models.Post;
import com.mercadolibre.socialmeli.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PostResponseDTO implements Comparable<PostResponseDTO> {

    private Integer id_post;
    private LocalDate date;
    private Product detail;
    private Integer category;
    private Double price;

    public PostResponseDTO(Post post) {
        this.id_post = post.getId_post();
        this.date = post.getDate();
        this.detail = post.getDetail();
        this.category = post.getCategory();
        this.price = post.getPrice();
    }

    @Override
    public int compareTo(PostResponseDTO p) {
        return this.getDate().compareTo(p.getDate());
    }
}
package com.mercadolibre.socialmeli.dtos.Product.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.socialmeli.models.Post;
import com.mercadolibre.socialmeli.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostUserDTO implements Comparable<PostUserDTO> {

    private Integer id_post;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private Product detail;
    private Integer category;
    private Double price;

    public PostUserDTO(Post post) {
        this.id_post = post.getId_post();
        this.date = post.getDate();
        this.detail = post.getDetail();
        this.category = post.getCategory();
        this.price = post.getPrice();
    }

    @Override
    public int compareTo(PostUserDTO u) {
        return u.getDate().compareTo(getDate());
    }
}
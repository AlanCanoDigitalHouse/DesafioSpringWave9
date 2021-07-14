package com.example.socialmeli.dtos.response;

import com.example.socialmeli.domains.Post;
import com.example.socialmeli.domains.Product;
import com.example.socialmeli.handlers.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailResponseDTO {

    private Integer idPost;
    private String date;
    private Product detail;
    private String category;
    private Double price;


    public PostDetailResponseDTO(Post post){
        this.idPost = post.getIdPost();
        this.date = DateUtils.dateToString(post.getDate());
        this.detail = post.getDetail();
        this.category = post.getCategory();
        this.price = post.getPrice();
    }

}

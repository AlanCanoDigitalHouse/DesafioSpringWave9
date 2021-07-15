package com.example.demo.Services.Mapper;

import com.example.demo.DTOs.PostDTO;
import com.example.demo.DTOs.ProductDTO;
import com.example.demo.Models.PostModel;
import com.example.demo.Models.ProductModel;

public class PostMapper {

    public static ProductModel productToModel(ProductDTO prodDTO){
        ProductModel product = new ProductModel(
                prodDTO.getProductName(),
                prodDTO.getType(),
                prodDTO.getBrand(),
                prodDTO.getColor(),
                prodDTO.getNotes());
        return product;
    }

    public static PostModel postToModel(PostDTO postDTO){
        PostModel post = new PostModel(
                postDTO.getUserId(),
                postDTO.getDate(),
                postDTO.getCategory(),
                postDTO.getPrice());
        return post;
    }

    /*   public static PostDTO postToDTO(PostModel post, ProductModel product){
        ProductDTO prodDTO = productToDTO(product);
        return  new PostDTO(
                post.getUserId(),
                post.getDate(),
                prodDTO,
                post.getCategoryId(),
                post.getPrice()
        );
    }*/

    /*    public static ProductDTO productToDTO(ProductModel product){
        return new ProductDTO(
                product.getProductName(),
                product.getType(),
                product.getBrand(),
                product.getColor(),
                product.getNotes()
        );
    }*/

}

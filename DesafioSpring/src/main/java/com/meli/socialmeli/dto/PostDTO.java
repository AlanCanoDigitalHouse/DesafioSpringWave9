package com.meli.socialmeli.dto;

import com.meli.socialmeli.model.Post;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PostDTO {
  private LocalDate date;
  private ProductDTO detail;
  private Integer category;
  private Double price;

  public PostDTO(LocalDate date, ProductDTO detail, Integer category, Double price) {
    this.date = date;
    this.detail = detail;
    this.category = category;
    this.price = price;
  }

  public PostDTO(Post post) {
    this.date = post.getDate();
    this.detail = new ProductDTO(post.getDetail());
    this.category = post.getCategory();
    this.price = post.getPrice();
  }
}

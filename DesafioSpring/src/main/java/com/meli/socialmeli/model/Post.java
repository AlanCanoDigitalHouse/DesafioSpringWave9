package com.meli.socialmeli.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Post implements Comparable<Post> {
  private Integer userId;
  private LocalDate date;
  private Product detail;
  private Integer category;
  private Double price;

  public Post(Integer userId, LocalDate date, Product detail, Integer category, Double price) {
    this.userId = userId;
    this.date = date;
    this.detail = detail;
    this.category = category;
    this.price = price;
  }

  @Override
  public int compareTo(Post post) {
    return date.isBefore(post.date) ? -1 : date.isEqual(post.date) ? 0 : 1;
  }
}

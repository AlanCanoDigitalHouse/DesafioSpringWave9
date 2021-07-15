package com.meli.socialmeli.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Post implements Comparable<Post> {
  private Integer userId;
  private Integer postId;
  private LocalDate date;
  private Product detail;
  private Integer category;
  private Double price;
  private Boolean hasPromo;
  private Double discount;

  public Post(Integer userId, LocalDate date, Product detail, Integer category, Double price) {
    this.userId = userId;
    this.date = date;
    this.detail = detail;
    this.category = category;
    this.price = price;
  }

  public Post(Integer userId, Integer postId, LocalDate date, Product detail, Integer category, Double price, Boolean hasPromo, Double discount) {
    this.userId = userId;
    this.postId = postId;
    this.date = date;
    this.detail = detail;
    this.category = category;
    this.price = price;
    this.hasPromo = hasPromo;
    this.discount = discount;
  }

  public Post(Integer userId, LocalDate date, Product detail, Integer category, Double price, Boolean hasPromo, Double discount) {
    this.userId = userId;
    this.date = date;
    this.detail = detail;
    this.category = category;
    this.price = price;
    this.hasPromo = hasPromo;
    this.discount = discount;
  }

  @Override
  public int compareTo(Post post) {
    return date.isBefore(post.date) ? -1 : date.isEqual(post.date) ? 0 : 1;
  }
}

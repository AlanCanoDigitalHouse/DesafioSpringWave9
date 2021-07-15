package com.meli.itbootcamp.model;

import lombok.*;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Post {
    private Integer id_post;
    private Date date;
    private Integer category;
    private Product item;
    private Double price;

    public Post() {
        date = new Date();
    }

    public Post(Integer category, Product item, Double price) {
        date = new Date();
        this.category = category;
        this.item = item;
        this.price = price;
    }

    public Integer getId_post() {
        return id_post;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public Date getDate() {
        return date;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Post getPost(){
        return this;
    }
}



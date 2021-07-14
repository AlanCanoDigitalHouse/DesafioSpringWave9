package com.example.desafiospring.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class Post {
    private Integer userId;
    @JsonProperty("id_post")
    private Integer idPost;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private Integer productId;
    private Integer category;
    private BigDecimal price;
    private Boolean hasPromo;
    private BigDecimal discount;

    public Post() {
    }

    public Post(Integer userId, Integer idPost, Date date, Integer productId, Integer category, BigDecimal price) {
        this.userId = userId;
        this.idPost = idPost;
        this.date = date;
        this.productId = productId;
        this.category = category;
        this.price = price;
    }

    public Post(Integer userId, Integer idPost, Date date, Integer productId, Integer category, BigDecimal price, Boolean hasPromo, BigDecimal discount) {
        this.userId = userId;
        this.idPost = idPost;
        this.date = date;
        this.productId = productId;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}

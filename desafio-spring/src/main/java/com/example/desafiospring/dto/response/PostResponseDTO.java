package com.example.desafiospring.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDTO {
    private Integer userId;
    private String userName;
    @JsonProperty("id_post")
    private Integer idPost;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private ProductResponseDTO detail;
    private Integer category;
    private BigDecimal price;
    private Boolean hasPromo;
    private BigDecimal discount;

    public PostResponseDTO() {
    }

    public PostResponseDTO(Integer userId, String userName, Integer idPost, Date date, ProductResponseDTO detail, Integer category, BigDecimal price) {
        this.userId = userId;
        this.userName = userName;
        this.idPost = idPost;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public PostResponseDTO(Integer userId, String userName, Integer idPost, Date date, ProductResponseDTO detail, Integer category, BigDecimal price, Boolean hasPromo, BigDecimal discount) {
        this.userId = userId;
        this.userName = userName;
        this.idPost = idPost;
        this.date = date;
        this.detail = detail;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductResponseDTO getDetail() {
        return detail;
    }

    public void setDetail(ProductResponseDTO detail) {
        this.detail = detail;
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

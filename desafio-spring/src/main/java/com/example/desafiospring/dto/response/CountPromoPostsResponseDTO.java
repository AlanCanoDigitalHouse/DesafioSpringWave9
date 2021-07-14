package com.example.desafiospring.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountPromoPostsResponseDTO {
    private Long userId;
    private String userName;
    @JsonProperty("promoproducts_count")
    private Integer promoProductsCount;

    public CountPromoPostsResponseDTO() {
    }

    public CountPromoPostsResponseDTO(Long userId, String userName, Integer promoProductsCount) {
        this.userId = userId;
        this.userName = userName;
        this.promoProductsCount = promoProductsCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPromoProductsCount() {
        return promoProductsCount;
    }

    public void setPromoProductsCount(Integer promoProductsCount) {
        this.promoProductsCount = promoProductsCount;
    }
}

package com.example.desafiospring.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
    protected Integer userId;
    protected String userName;
    private List<SellerResponseDTO> followed;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserResponseDTO(Integer userId, String userName, List<SellerResponseDTO> followed) {
        this.userId = userId;
        this.userName = userName;
        this.followed = followed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<SellerResponseDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<SellerResponseDTO> followed) {
        this.followed = followed;
    }
}

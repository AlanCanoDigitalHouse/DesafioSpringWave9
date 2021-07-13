package com.example.desafiospring.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
    protected Long userId;
    protected String userName;
    private List<SellerResponseDTO> followed;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserResponseDTO(Long userId, String userName, List<SellerResponseDTO> followed) {
        this.userId = userId;
        this.userName = userName;
        this.followed = followed;
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

    public List<SellerResponseDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<SellerResponseDTO> followed) {
        this.followed = followed;
    }
}

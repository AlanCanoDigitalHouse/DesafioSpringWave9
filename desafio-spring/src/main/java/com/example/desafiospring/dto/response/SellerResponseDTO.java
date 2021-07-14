package com.example.desafiospring.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerResponseDTO extends UserResponseDTO {
    private Integer followers_count;
    private List<UserResponseDTO> followers;

    public SellerResponseDTO() {
    }

    public SellerResponseDTO(Integer followers_count) {
        this.followers_count = followers_count;
    }

    public SellerResponseDTO(Integer userId, String userName, Integer followers_count) {
        super(userId, userName);
        this.followers_count = followers_count;
    }

    public SellerResponseDTO(Integer userId, String userName, List<UserResponseDTO> followers) {
        super(userId, userName);
        this.followers = followers;
    }

    public Integer getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(Integer followers_count) {
        this.followers_count = followers_count;
    }

    public List<UserResponseDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserResponseDTO> followers) {
        this.followers = followers;
    }
}

package com.example.desafiospring.dtos.response;

import com.example.desafiospring.dtos.createData.Sellers;

import java.util.List;

public class SellersFollowedListDto {
    private int userId;
    private String userName;
    private List<Sellers> followed;

    public SellersFollowedListDto() {
    }

    public SellersFollowedListDto(int userId, String userName, List<Sellers> followed) {
        this.userId = userId;
        this.userName = userName;
        this.followed = followed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Sellers> getFollowed() {
        return followed;
    }

    public void setFollowed(List<Sellers> followed) {
        this.followed = followed;
    }
}

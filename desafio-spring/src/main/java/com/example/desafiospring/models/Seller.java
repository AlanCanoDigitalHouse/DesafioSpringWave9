package com.example.desafiospring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Seller extends User {

    @JsonProperty("followersIds")
    private List<Long> followers;

    public Seller() {
    }

    public Seller(String userName, Long userId, List<Long> followers) {
        super(userName, userId);
        this.followers = followers;
    }

    public List<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Long> followers) {
        this.followers = followers;
    }
}

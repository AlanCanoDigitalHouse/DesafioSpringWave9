package com.example.desafiospring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Seller extends User {

    @JsonProperty("followersIds")
    private List<Integer> followers;

    public Seller() {
    }

    public Seller(String userName, Integer userId, List<Integer> followers) {
        super(userName, userId);
        this.followers = followers;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }
}

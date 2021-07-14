package com.example.desafiospring.dtos.createData;


import java.util.concurrent.atomic.AtomicInteger;

public class Sellers {
    private Integer userId;
    private String userName;
    private static final AtomicInteger count = new AtomicInteger(0);

    public Sellers(String userName) {
        this.userId = count.incrementAndGet();
        this.userName = userName;
    }

    public Sellers() {
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
}

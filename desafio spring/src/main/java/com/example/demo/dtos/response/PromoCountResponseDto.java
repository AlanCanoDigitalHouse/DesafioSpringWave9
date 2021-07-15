package com.example.demo.dtos.response;

public class PromoCountResponseDto {

    private int userId;
    private String userName;
    private int counter;

    public PromoCountResponseDto(int userId, String userName, int counter) {
        this.userId = userId;
        this.userName = userName;
        this.counter = counter;
    }
}

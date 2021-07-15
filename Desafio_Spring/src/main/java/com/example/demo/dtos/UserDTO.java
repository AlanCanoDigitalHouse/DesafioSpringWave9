package com.example.demo.dtos;

public class UserDTO {

    private Integer userId;
    private String userName;

    public UserDTO(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserDTO() {
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}

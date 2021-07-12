package com.example.desafiospring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Long userId;
    private String userName;
    private boolean isSeller;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isSeller == user.isSeller && userId.equals(user.userId) && userName.equals(user.userName);
    }

}

package com.desafiospring.socialmeli.dtos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable<User> {

    private Integer userId;
    private String userName;

    @Override
    public int compareTo(User o) {
        return this.getUserName().compareTo(o.getUserName());
    }
}

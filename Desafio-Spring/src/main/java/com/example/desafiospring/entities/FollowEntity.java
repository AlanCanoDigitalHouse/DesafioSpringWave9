package com.example.desafiospring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowEntity {
    private Integer followerUserID;
    private Integer followedUserID;
}

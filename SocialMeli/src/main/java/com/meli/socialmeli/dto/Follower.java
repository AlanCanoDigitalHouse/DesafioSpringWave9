package com.meli.socialmeli.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follower {
    private Integer userId;
    private String userName;
}

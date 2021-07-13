package com.jbianchini.meli.socialmeli.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowersCountDTO {
    private Integer userId;
    private String userName;
    private int followers_count;
}
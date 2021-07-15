package com.example.demo.dtos.response;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class FollowersCount {

    private Integer userId;
    private String userName;
    private Integer followers_count;

}

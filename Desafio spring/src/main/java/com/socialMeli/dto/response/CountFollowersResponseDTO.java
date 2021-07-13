package com.socialMeli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountFollowersResponseDTO {
    private int userId;
    private String userName;
    private int followers_count;
}

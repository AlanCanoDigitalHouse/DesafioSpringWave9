package com.socialmeli.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountFollowersResponseDTO {

    private Integer userId;
    private String userName;

    @JsonProperty(value = "followers_count")
    private Integer followersCount;
}

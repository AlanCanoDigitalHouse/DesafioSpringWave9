package com.socialMeli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FollowResultResponseDTO {
    private String userName;
    private int userId;
    private String followUserName;
    private int followUserId;
}

package com.jbianchini.meli.socialmeli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowersListResponse {
    private int userId;
    private String userName;
    private List<UserResponse> followers;
}

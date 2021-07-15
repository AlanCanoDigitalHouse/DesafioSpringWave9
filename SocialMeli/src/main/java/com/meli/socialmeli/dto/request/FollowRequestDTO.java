package com.meli.socialmeli.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowRequestDTO {
    private Integer userId;
    private Integer userIdToFollow;
}
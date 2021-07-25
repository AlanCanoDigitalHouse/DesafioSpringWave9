package com.meli.socialmeli.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowCountResponseDTO extends UserResponseDTO {
    private Integer followCount;

    public FollowCountResponseDTO(Integer userId, String userName, Integer followCount) {
        super(userId, userName);
        this.followCount = followCount;
    }
}

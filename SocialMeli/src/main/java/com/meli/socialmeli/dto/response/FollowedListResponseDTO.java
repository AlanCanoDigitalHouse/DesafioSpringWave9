package com.meli.socialmeli.dto.response;

import com.meli.socialmeli.dto.Follower;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowedListResponseDTO extends UserResponseDTO{
    private List<Follower> followed;

    public FollowedListResponseDTO(Integer userId, String userName, List<Follower> followed) {
        super(userId, userName);
        this.followed= followed;
    }
}

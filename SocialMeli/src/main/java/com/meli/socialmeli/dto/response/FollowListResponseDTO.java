package com.meli.socialmeli.dto.response;

import com.meli.socialmeli.dto.Follower;
import com.meli.socialmeli.dto.FollowerB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowListResponseDTO extends UserResponseDTO{
    private List<Follower> follower;

    public FollowListResponseDTO(Integer userId, String userName, List<Follower> follower) {
        super(userId, userName);
        this.follower= follower;
    }

}

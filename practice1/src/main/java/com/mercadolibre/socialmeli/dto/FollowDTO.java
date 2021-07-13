package com.mercadolibre.socialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowDTO {
    private Integer follower;
    private Integer followed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowDTO)) return false;
        FollowDTO followDTO = (FollowDTO) o;
        return getFollower().equals(followDTO.getFollower()) && getFollowed().equals(followDTO.getFollowed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFollower(), getFollowed());
    }
}

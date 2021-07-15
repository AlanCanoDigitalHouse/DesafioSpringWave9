package com.meli.socialmeli.dto;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer userId;
    private String userName;
    private List<Follower> followers;
    private List<Follower> followed;
    private List<Integer> posts;
    private List<Integer> specialPosts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userId, userDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}

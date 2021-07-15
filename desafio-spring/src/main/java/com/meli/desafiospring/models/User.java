package com.meli.desafiospring.models;

import com.meli.desafiospring.DTOs.response.PostResponseDTO;
import com.meli.desafiospring.DTOs.request.PostRequestDTO;
import com.meli.desafiospring.DTOs.response.SimpleUserDTO;
import com.meli.desafiospring.DTOs.response.UserResponseDTO;
import com.meli.desafiospring.exceptions.custom.FollowUnfollowException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer userId;
    private String userName;
    private List<PostResponseDTO> postResponseDTOS;
    private List<User> followers;
    private List<User> followed;


    public void follow(User followed) {
        if (this.userId == followed.getUserId())
            throw new FollowUnfollowException("User can't follow himself.");
        if (!this.followed.contains(followed))
            this.followed.add(followed);
        if (!followed.getFollowers().contains(this))
            followed.getFollowers().add(this);
    }

    public void unfollow(User unfollowed) {
        if (this.userId == unfollowed.getUserId())
            throw new FollowUnfollowException("User can't unfollow himself.");
        this.followed.remove(unfollowed);
        unfollowed.getFollowers().remove(this);
    }

    public List<PostResponseDTO> getFollowedLastPostedItems() {
        Calendar last2weeks = Calendar.getInstance();
        last2weeks.add(Calendar.DAY_OF_MONTH,-14);
        Date last2weeksTime = last2weeks.getTime();
        return followed
                .stream()
                .flatMap(f -> f.getPostResponseDTOS().stream()).filter(p -> last2weeksTime.before(p._getTime()))
                .collect(Collectors.toList());
    }

    public SimpleUserDTO toSimpleUser() {
        List<UserResponseDTO> followers = new ArrayList<>();
        for (User u : this.followers) {
            followers.add(new UserResponseDTO(u.getUserId(), u.getUserName()));
        }

        List<UserResponseDTO> followed = new ArrayList<>();
        for (User u : this.followed) {
            followed.add(new UserResponseDTO(u.getUserId(), u.getUserName()));
        }

        return new SimpleUserDTO(this.getUserId(), this.getUserName(), followers, followed);
    }

    public PostResponseDTO newPost(PostRequestDTO postRequestDTO, Long id_post, Long product_id) {
        PostResponseDTO postResponseDTO = postRequestDTO.toPostResponseDTO(id_post, product_id);
        postResponseDTOS.add(postResponseDTO);
        return postResponseDTO;
    }
}

package com.desafiospring.socialmeli.dtos.responses;

import com.desafiospring.socialmeli.dtos.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromoPostsDTO {

    private int userId;
    private String userName;
    private List<Post> posts;

}

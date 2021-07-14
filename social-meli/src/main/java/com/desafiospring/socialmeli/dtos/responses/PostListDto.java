package com.desafiospring.socialmeli.dtos.responses;


import com.desafiospring.socialmeli.dtos.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostListDto {

    private int userId;
    private List<Post> posts;

}

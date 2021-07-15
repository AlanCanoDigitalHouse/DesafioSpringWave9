package com.meli.joescaos.socialmeli.socialmeli.dtos.responses;

import com.meli.joescaos.socialmeli.socialmeli.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.support.DefaultDataBinderFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsListDto {

    private int userId;
    private List<Post> posts = new ArrayList<>();


    public void addPost(Post post){ this.posts.add(post) ;}

}

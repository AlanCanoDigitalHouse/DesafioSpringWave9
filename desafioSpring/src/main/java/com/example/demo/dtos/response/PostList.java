package com.example.demo.dtos.response;

import com.example.demo.dtos.request.Post;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
public class PostList {

    private Integer userId;
    private List<Post> posts;

}

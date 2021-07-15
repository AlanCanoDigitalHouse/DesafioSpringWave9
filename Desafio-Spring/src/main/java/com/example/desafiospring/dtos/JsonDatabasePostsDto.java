package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class JsonDatabasePostsDto {

    @Getter
    private Map<Integer, Map<Integer,Post>> userPosts;

    public void addPost(Post post) {
        if(userPosts.containsKey(post.getUserId())) {
            userPosts.get(post.getUserId()).put(post.getPostId(), post);
        } else {
            userPosts.computeIfAbsent(post.getUserId(), userId -> Map.of(post.getPostId(), post));
        }
    }
}

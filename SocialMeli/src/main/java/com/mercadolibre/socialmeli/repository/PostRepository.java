package com.mercadolibre.socialmeli.repository;

import com.mercadolibre.socialmeli.dto.request.PostRequestDTO;
import com.mercadolibre.socialmeli.dto.response.PostResponseDTO;
import com.mercadolibre.socialmeli.entity.Post;
import com.mercadolibre.socialmeli.entity.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

    private final List<Post> postsDatabase = new ArrayList<>();

    public Integer createPost(PostRequestDTO postRequestDTO){
        int postId, productId;
        postId = productId = postsDatabase.size() + 1;
        postRequestDTO.getDetail().setProduct_id(productId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateTime = LocalDate.parse(postRequestDTO.getDate(), formatter);
        Post post = new Post(
                postRequestDTO.getUserId(),
                postId,
                dateTime,
                postRequestDTO.getDetail(),
                postRequestDTO.getCategory(),
                postRequestDTO.getPrice()
        );
        postsDatabase.add(post);
        return postId;
    }

    public List<Post> getPostsFollowed(User user){
        LocalDate twoWeeksAgo = LocalDate.now().minusWeeks(2);
        List<Post> posts = postsDatabase.stream()
                .filter(
                        post -> user.getFollowed().contains(post.getUserId())
                                && post.getCreatedDate().isAfter(twoWeeksAgo)
                ).sorted(Comparator.comparing(Post::getCreatedDate))
                .collect(Collectors.toList());
        return posts;
    }

}

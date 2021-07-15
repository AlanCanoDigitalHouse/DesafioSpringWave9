package com.mercadolibre.socialmeli.repository;

import com.mercadolibre.socialmeli.dto.request.PostPromoRequestDTO;
import com.mercadolibre.socialmeli.dto.request.PostRequestDTO;
import com.mercadolibre.socialmeli.dto.response.PostCountPromoResponseDTO;
import com.mercadolibre.socialmeli.dto.response.PostResponseDTO;
import com.mercadolibre.socialmeli.entity.Post;
import com.mercadolibre.socialmeli.entity.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class PostRepository {

    private final List<Post> postsDatabase = new ArrayList<>();
    private final LocalDate twoWeeksAgo = LocalDate.now().minusWeeks(2);

    public PostCountPromoResponseDTO countPromosInPostsByUser(User user){
        int countPostWithPromo = this.postsDatabase
                .stream()
                .filter(post -> post.getHasPromo().equals(true))
                .collect(Collectors.toList())
                .size();
        return new PostCountPromoResponseDTO(user.getUserId(), user.getUserName(), countPostWithPromo);
    }
    public Integer createPost(PostRequestDTO postRequestDTO){
        int postId, productId;
        postId = productId = postsDatabase.size() + 1;
        postRequestDTO.getDetail().setProduct_id(productId);
        Post post = new Post(
                postRequestDTO.getUserId(),
                postId,
                postRequestDTO.getDate(),
                postRequestDTO.getDetail(),
                postRequestDTO.getCategory(),
                postRequestDTO.getPrice(),
                postRequestDTO.getHasPromo(),
                null
        );
        postsDatabase.add(post);
        return postId;
    }

    public Integer createPostPromo(PostPromoRequestDTO postRequestDTO){
        int postId, productId;
        postId = productId = postsDatabase.size() + 1;
        postRequestDTO.getDetail().setProduct_id(productId);
        Post post = new Post(
                postRequestDTO.getUserId(),
                postId,
                postRequestDTO.getDate(),
                postRequestDTO.getDetail(),
                postRequestDTO.getCategory(),
                postRequestDTO.getPrice(),
                postRequestDTO.getHasPromo(),
                postRequestDTO.getDiscount()
        );
        postsDatabase.add(post);
        return postId;
    }

    public List<Post> getPostsFollowed(User user, String order){
        Stream<Post> posts = postsDatabase.stream()
                .filter(
                        post -> user.getFollowed().contains(post.getUserId())
                                && post.getDate().isAfter(this.twoWeeksAgo)
                );
        if (order.equals("date_asc")) return posts.sorted(Comparator.comparing(Post::getDate)).collect(Collectors.toList());
        if (order.equals("date_desc")) return posts.sorted(Comparator.comparing(Post::getDate).reversed()).collect(Collectors.toList());

        return new ArrayList<>();
    }

    public List<Post> getPostsWithPromoByUser(User user){
        Stream<Post> posts = postsDatabase.stream()
                .filter(
                        post -> user.getUserId() == post.getUserId()
                                && post.getHasPromo().equals(true)
                                && post.getDate().isAfter(this.twoWeeksAgo)
                );

        return posts.sorted(Comparator.comparing(Post::getDate)).collect(Collectors.toList());
        //return new ArrayList<>();
    }
}

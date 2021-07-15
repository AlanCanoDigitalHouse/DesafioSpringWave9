package bootcamp.wave9.SocialMeli.repository;


import bootcamp.wave9.SocialMeli.entity.Post;

import java.util.List;

public interface PostRepository {
    Post findPostById(int postId);
    Post save(Post post);
    List<Post> findAll();
    Post deletePostById(int postId);
}

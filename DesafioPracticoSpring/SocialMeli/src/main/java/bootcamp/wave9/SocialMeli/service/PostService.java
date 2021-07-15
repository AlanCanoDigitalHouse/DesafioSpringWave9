package bootcamp.wave9.SocialMeli.service;

import bootcamp.wave9.SocialMeli.entity.Post;
import bootcamp.wave9.SocialMeli.exception.PostNotFoundException;
import bootcamp.wave9.SocialMeli.exception.UserNotFoundException;

import java.util.List;
import java.util.Map;

public interface PostService {

    void createPost(Post post) throws UserNotFoundException;
    Map<String,Object> getFollowedPosts(int userId, String order) throws UserNotFoundException;
    Map<String,Object> getCountPromo(int userId) throws UserNotFoundException;
    Map<String,Object> getPromoList(int userId) throws UserNotFoundException;
    List<Post> getPostList();
    Post getPostById(int postId) throws PostNotFoundException;
    Post deletePostById(int postId) throws PostNotFoundException;
}

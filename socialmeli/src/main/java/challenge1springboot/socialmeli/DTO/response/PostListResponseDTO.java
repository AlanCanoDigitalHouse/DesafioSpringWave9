package challenge1springboot.socialmeli.DTO.response;

import challenge1springboot.socialmeli.DTO.PostDTO;
import challenge1springboot.socialmeli.entities.Post;
import challenge1springboot.socialmeli.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostListResponseDTO {

    int userId;
    List<PostDTO> posts;

    public PostListResponseDTO(User user, List<Post> posts) {
        userId = user.getUserId();
        this.posts = new ArrayList<>();
        posts.forEach(post -> this.posts.add(
                new PostDTO(
                        post.getId_post(),
                        post.getDate(),
                        post.getDetail(),
                        post.getCategory(),
                        post.getPrice()))
        );
    }
}
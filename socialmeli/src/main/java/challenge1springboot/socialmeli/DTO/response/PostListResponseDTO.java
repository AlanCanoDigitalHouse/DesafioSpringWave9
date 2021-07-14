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
/*
{
        "userId": 4698,
        "posts": [
        {
        "id_post" : 32,
        "date" : "01-05-2021”,
        "detail" :
        { “product_id” : 62,
        "productName" : "Headset RGB Inalámbrico",
        "type" : "Gamer",,
        "brand" : "Razer"
        "color" : "Green with RGB",
        "notes" : "Sin Batería"
        },
        "category" : "120",
        "price" : 2800.69,
        },
        {
        "id_post" : 18,
        "date" : "29-04-2021”,
        "detail" :
        { “product_id” : 1,
        "productName" : "Silla Gamer",
        "type" : "Gamer",,
        "brand" : "Racer"
        "color" : "Red & Black",
        "notes" : "Special Edition"
        },
        "category" : "100",
        "price" : 15000.50,
        }
        ]
        }*/

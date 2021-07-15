package desafiospringw9.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostListDTO {

    private int userId;
    private String userName;
    private List<PostDTO> post;
}

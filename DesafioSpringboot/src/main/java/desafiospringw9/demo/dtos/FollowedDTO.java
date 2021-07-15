package desafiospringw9.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class FollowedDTO {
    private int userId;
    private List<PostDTO> posts;
}

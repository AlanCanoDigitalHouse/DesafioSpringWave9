package challenge1springboot.socialmeli.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCountFollowersResponseDTO {
    private int userId;
    private String userName;
    private int followers_count;
}

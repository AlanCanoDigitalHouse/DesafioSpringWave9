package challenge1springboot.socialmeli.DTO.response;


import challenge1springboot.socialmeli.DTO.FollowDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserListFollowersResponseDTO {
    private int userId;
    private String userName;
    private List<FollowDTO> followers;
}

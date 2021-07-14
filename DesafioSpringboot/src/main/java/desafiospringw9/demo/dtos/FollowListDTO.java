package desafiospringw9.demo.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FollowListDTO {
    private int userId;
    private String userName;
    private List<UserDTO> related;
}

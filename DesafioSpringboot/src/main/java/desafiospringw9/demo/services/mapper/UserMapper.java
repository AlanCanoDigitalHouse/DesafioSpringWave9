package desafiospringw9.demo.services.mapper;

import desafiospringw9.demo.dtos.UserDTO;
import desafiospringw9.demo.models.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static List<UserDTO> toDTO(List<UserModel> users){
        return users.stream().map(u -> new UserDTO(u.getUserId(), u.getUserName())).collect(Collectors.toList());
    }
}

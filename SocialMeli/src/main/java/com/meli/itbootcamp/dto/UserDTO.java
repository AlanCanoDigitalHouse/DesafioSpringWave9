package com.meli.itbootcamp.dto;

import com.meli.itbootcamp.model.User;
import lombok.*;

@Getter
@Setter
@ToString

public class UserDTO extends User{

    public UserDTO(User user){
        super(user.getUserID(), user.getUserName());
//        this.userID= user.getUserID();
//        this.userName = user.getUserName();
    }
}

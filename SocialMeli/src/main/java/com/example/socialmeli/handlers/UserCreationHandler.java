package com.example.socialmeli.handlers;
import com.example.socialmeli.dtos.UserDto;
import com.example.socialmeli.models.User;
import java.util.List;

public class UserCreationHandler {

    public static List<User> create(List users) {

        User vendedor=new User();
        vendedor.setUserId(10);
        vendedor.setUserName("Alberto");


        User seguidor1=new User();
        seguidor1.setUserId(23);
        seguidor1.setUserName("Aldana");
        vendedor.addFollower(new UserDto(seguidor1.getUserId(),seguidor1.getUserName()));
        seguidor1.addFollowed(new UserDto(vendedor.getUserId(),vendedor.getUserName()));
        users.add(seguidor1);
        User usuario1=new User();
        usuario1.setUserId(11);
        usuario1.setUserName("Marcos");

        users.add(vendedor);
        users.add(usuario1);

        User vendedor2=new User();
        vendedor2.setUserId(12);
        vendedor2.setUserName("Uriel");

        User usuario2=new User();
        usuario2.setUserId(13);
        usuario2.setUserName("Micaela");
        users.add(vendedor2);
        users.add(usuario2);


        return users;
    }
}

package com.apismeli.socialmeli.utilities;

import com.apismeli.socialmeli.dtos.request.UserDTO;

import java.util.Comparator;

public class compareUsernameAsc implements Comparator<UserDTO> {


    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        return o1.getUserName().compareTo(o2.getUserName());
    }
}

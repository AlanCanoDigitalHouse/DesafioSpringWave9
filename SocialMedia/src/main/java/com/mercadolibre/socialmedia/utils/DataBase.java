package com.mercadolibre.socialmedia.utils;

import com.mercadolibre.socialmedia.dtos.User;
import com.mercadolibre.socialmedia.dtos.UserDto;

import java.text.ParseException;
import java.util.*;

public class DataBase {
    public static HashMap<Integer, UserDto> initDataBase() throws ParseException {
        UserDto user0 = new UserDto(0, "lllll", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        UserDto user1 = new UserDto(1, "fffff", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        UserDto user2 = new UserDto(2, "xxxxx", new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
        UserDto user3 = new UserDto(3, "bbbbb", new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
        UserDto user4 = new UserDto(4, "rrrrr", new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
        UserDto user5 = new UserDto(5, "ppppp", new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
        UserDto user6 = new UserDto(6, "zzzzz", new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
        UserDto user7 = new UserDto(7, "aaaaa", new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
        
        return new HashMap<>() {{
             put(0, user0);
             put(1, user1);
             put(2, user2);
             put(3, user3);
             put(4, user4);
             put(5, user5);
             put(6, user6);
             put(7, user7);
         }};
    }
}

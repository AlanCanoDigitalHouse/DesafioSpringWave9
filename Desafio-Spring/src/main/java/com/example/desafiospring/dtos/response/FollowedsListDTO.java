package com.example.desafiospring.dtos.response;

import com.example.desafiospring.dtos.general.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowedsListDTO {

    private Integer userId;
    private String userName;
    private List<User> followeds;

}

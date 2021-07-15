package com.mercadolibre.desafio.spring.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import java.util.ArrayList;
import java.util.Objects;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Integer userId;
    private String userName;
    private ArrayList<Integer> followed= new ArrayList<>();
    private ArrayList<Integer> followers= new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userId, userDto.userId) && Objects.equals(userName, userDto.userName) && Objects.equals(followed, userDto.followed) && Objects.equals(followers, userDto.followers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, followed, followers);
    }
}

package com.mercadolibre.socialmeli.dto;

import com.mercadolibre.socialmeli.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private Date createAt;
    private String userName;
    private String userType;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.createAt = user.getCreateAt();
        this.userName = user.getUserName();
        this.userType = user.getUserType();
    }

    public UserDto(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}

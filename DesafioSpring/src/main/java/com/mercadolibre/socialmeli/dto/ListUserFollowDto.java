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
public class ListUserFollowDto {
    private Integer userId;
    private String  userName;

    public ListUserFollowDto(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
    }
}

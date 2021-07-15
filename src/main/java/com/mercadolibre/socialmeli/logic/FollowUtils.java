package com.mercadolibre.socialmeli.logic;

import com.mercadolibre.socialmeli.entity.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FollowUtils {

    public final List<Integer> listIdUserToFollow(final List<Follow> listFollow){
        return listFollow.stream().map((Follow::getUserId)).collect(Collectors.toList());
    }
    public final List<Integer> listIdUser(final List<Follow> listFollow){
        return listFollow.stream().map((Follow::getUserIdToFollow)).collect(Collectors.toList());
    }
}

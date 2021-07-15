package com.example.desafiospringboot.service;

import com.example.desafiospringboot.dto.FollowCount;
import com.example.desafiospringboot.dto.FollowersDetail;

public interface FollowService {
    public boolean seguir(int seguidor, int seguido);
    public FollowCount getFollowersCount(int usuario);
    public FollowersDetail getFollowersDetail(int usuario);
    public FollowersDetail getFollowedDetail(int usuario);
    public FollowersDetail getFollowersDetailOrdered(int usuario, boolean asc);
    public FollowersDetail getFollowedDetailOrdered(int userId, boolean asc);
    public Boolean checkFollow(int seguidor, int seguido);
    /**
     * 
     * @param seguidor
     * Usuario que deja de seguir
     * @param seguido
     * Usuario al que dejan de seguir
     * @return
     * confirmación de la transacción
     */
    public boolean unfollow (int seguidor, int seguido);
}

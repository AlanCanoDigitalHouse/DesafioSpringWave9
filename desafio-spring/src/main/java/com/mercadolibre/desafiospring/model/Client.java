package com.mercadolibre.desafiospring.model;

import com.mercadolibre.desafiospring.exception.user.*;
import com.mercadolibre.desafiospring.util.SortUtils;
import lombok.*;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class Client extends User {
    private List<Seller> followed;

    public Client(int id, String name) {
        super(id, name);
        this.followed = new ArrayList<>();
    }

    public void follow(Seller seller) throws AlreadyFollowingException {
        if ( this.followed.contains(seller) ) {
            throw new AlreadyFollowingException();
        }

        this.followed.add(seller);
    }

    public void unfollow(Seller seller) throws NotAFollowerException {
        if ( ! this.followed.contains(seller) ) {
            throw new NotAFollowerException();
        }

        this.followed.remove(seller);
    }

    public List<Seller> getSortedFollowed(String order) {
        return this.followed.stream().sorted(User.sortings.get(order))
                .collect(Collectors.toList());
    }
}

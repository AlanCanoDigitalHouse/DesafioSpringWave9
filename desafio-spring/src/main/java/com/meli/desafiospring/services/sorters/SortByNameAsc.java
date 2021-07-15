package com.meli.desafiospring.services.sorters;

import com.meli.desafiospring.models.User;

import java.util.Comparator;

public class SortByNameAsc implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getUserName().compareTo(o2.getUserName());
    }
}

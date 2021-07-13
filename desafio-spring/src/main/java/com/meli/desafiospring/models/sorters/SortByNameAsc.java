package com.meli.desafiospring.models.sorters;

import com.meli.desafiospring.models.User;

import java.util.Comparator;

public class SortByNameAsc implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o2.getUserName().compareTo(o1.getUserName());
    }
}

package com.meli.socialmeli.utils;

import com.meli.socialmeli.models.User;

import java.util.Comparator;

public class SortUserByName implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o2.getUserName().compareTo(o1.getUserName());
    }

    @Override
    public Comparator<User> reversed() {
        return Comparator.super.reversed();
    }
}

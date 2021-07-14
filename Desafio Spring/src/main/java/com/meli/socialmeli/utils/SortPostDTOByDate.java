package com.meli.socialmeli.utils;

import com.meli.socialmeli.dtos.response.PostDTO;

import java.util.Comparator;

public class SortPostDTOByDate implements Comparator<PostDTO> {

    @Override
    public int compare(PostDTO o1, PostDTO o2) {
        return o2.getDate().compareTo(o1.getDate());
    }

    @Override
    public Comparator<PostDTO> reversed() {
        return Comparator.super.reversed();
    }
}

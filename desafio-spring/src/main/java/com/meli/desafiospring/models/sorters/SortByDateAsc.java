package com.meli.desafiospring.models.sorters;

import com.meli.desafiospring.DTOs.PostDTO;
import com.meli.desafiospring.models.User;

import java.util.Comparator;

public class SortByDateAsc implements Comparator<PostDTO> {

    @Override
    public int compare(PostDTO o1, PostDTO o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}

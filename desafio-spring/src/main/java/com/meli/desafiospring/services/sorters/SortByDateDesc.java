package com.meli.desafiospring.services.sorters;

import com.meli.desafiospring.DTOs.response.PostResponseDTO;

import java.util.Comparator;

public class SortByDateDesc implements Comparator<PostResponseDTO> {

    @Override
    public int compare(PostResponseDTO o1, PostResponseDTO o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}

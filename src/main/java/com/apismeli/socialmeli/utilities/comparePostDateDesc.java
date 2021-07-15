package com.apismeli.socialmeli.utilities;

import com.apismeli.socialmeli.dtos.response.PostResponseDTO;

import java.util.Comparator;

public class comparePostDateDesc implements Comparator<PostResponseDTO> {
    @Override
    public int compare(PostResponseDTO o1, PostResponseDTO o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}

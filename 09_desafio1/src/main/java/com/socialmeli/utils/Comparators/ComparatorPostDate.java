package com.socialmeli.utils.Comparators;

import com.socialmeli.dtos.response.PostResponseDTO;

public class ComparatorPostDate implements IComparator<PostResponseDTO> {

    @Override
    public int compareAsc(PostResponseDTO a, PostResponseDTO b) {
        return a.getDate().compareTo(b.getDate());
    }

    @Override
    public int compareDesc(PostResponseDTO a, PostResponseDTO b) {
        return b.getDate().compareTo(a.getDate());
    }

    @Override
    public int compare(PostResponseDTO o1, PostResponseDTO o2) {
        return 0;
    }
}

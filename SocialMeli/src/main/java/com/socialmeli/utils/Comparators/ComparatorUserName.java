package com.socialmeli.utils.Comparators;

import com.socialmeli.dtos.UserDTO;

public class ComparatorUserName implements IComparator<UserDTO> {

    @Override
    public int compareAsc(UserDTO a, UserDTO b) {
        return a.getUserName().compareTo(b.getUserName());
    }

    @Override
    public int compareDesc(UserDTO a, UserDTO b) {
        return b.getUserName().compareTo(a.getUserName());
    }

    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        return 0;
    }
}

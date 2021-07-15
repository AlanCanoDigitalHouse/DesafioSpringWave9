package com.socialmeli.utils.Comparators;

import java.util.Comparator;

public interface IComparator<T> extends Comparator<T> {

    int compareAsc(T a, T b);

    int compareDesc(T a, T b);
}

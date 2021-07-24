package com.socialmeli.utils;

import com.socialmeli.utils.Comparators.IComparator;

import java.util.ArrayList;

public class SortUtils {

    public static <T> void sortAsc(ArrayList<T> arr, IComparator<T> c) {
        arr.sort(c::compareAsc);
    }

    public static <T> void sortDesc(ArrayList<T> arr, IComparator<T> c) {
        arr.sort(c::compareDesc);
    }
}

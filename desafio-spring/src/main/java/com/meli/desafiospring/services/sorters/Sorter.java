package com.meli.desafiospring.services.sorters;

import com.meli.desafiospring.DTOs.response.PostResponseDTO;
import com.meli.desafiospring.models.User;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sorter {


    public static Map<Object, Object> sorterMapper = Stream.of(new Object[][] {
            { "name_asc", new SortByNameAsc() },
            { "name_desc", new SortByNameDesc()},
            { "date_asc", new SortByDateAsc()},
            { "date_desc", new SortByDateDesc()}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public static Comparator<? super User> nameSorter(String order) {
        return (Comparator<? super User>) sorterMapper.get(order);
    }

    public static Comparator<? super PostResponseDTO> dateSorter(String order) {
        return (Comparator<? super PostResponseDTO>) sorterMapper.get(order);
    }

}

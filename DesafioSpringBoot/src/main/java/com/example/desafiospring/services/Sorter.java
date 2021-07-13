package com.example.desafiospring.services;

import java.util.List;

public interface Sorter<T> {
    List<T> sortDesc(List<T> list);
    List<T> sortAsc(List<T> list);
    List<T> sorterWrapper(List<T> list,String param);
}

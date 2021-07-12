package com.example.desafiospring.services;

import java.util.List;

public interface Sorter<T> {
    List<T> sortDesc();
    List<T> sortAsc();
}

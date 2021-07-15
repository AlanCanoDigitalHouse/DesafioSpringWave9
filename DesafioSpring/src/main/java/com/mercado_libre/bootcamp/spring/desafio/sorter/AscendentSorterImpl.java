package com.mercado_libre.bootcamp.spring.desafio.sorter;

import com.mercado_libre.bootcamp.spring.desafio.models.User;

import java.util.Comparator;
import java.util.List;

public class AscendentSorterImpl implements Sorter {

    @Override
    public void sort(List<User> users) {
        users.sort(Comparator.comparing(User::getUserName));
    }
}

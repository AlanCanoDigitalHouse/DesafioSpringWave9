package com.mercado_libre.bootcamp.spring.desafio.sorter.user;

import com.mercado_libre.bootcamp.spring.desafio.models.User;
import com.mercado_libre.bootcamp.spring.desafio.sorter.Sorter;

import java.util.List;

public class UserAscendentSorterImpl implements Sorter<User> {

    @Override
    public void sort(List<User> users) {
        users.sort((x, y) -> x.getUserName().compareToIgnoreCase(y.getUserName()));
    }
}

package com.mercado_libre.bootcamp.spring.desafio.sorter.post;

import com.mercado_libre.bootcamp.spring.desafio.models.Post;
import com.mercado_libre.bootcamp.spring.desafio.sorter.Sorter;

import java.util.List;

public class PostDescendentSorterImpl implements Sorter<Post> {

    @Override
    public void sort(List<Post> posts) {
        posts.sort((x, y) -> y.getDetail().getProductName().compareToIgnoreCase(x.getDetail().getProductName()));
    }
}

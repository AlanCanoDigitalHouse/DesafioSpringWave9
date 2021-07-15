package com.mercado_libre.bootcamp.spring.desafio.repositories.seller;

import com.mercado_libre.bootcamp.spring.desafio.models.Post;
import com.mercado_libre.bootcamp.spring.desafio.models.Seller;

public interface SellerRepository {

    public Seller getSeller(int userId);

    public void savePost(int userId, Post post);
}

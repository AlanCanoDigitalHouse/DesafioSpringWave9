package com.api.firstspringchallenge.repositories.seller;

import com.api.firstspringchallenge.models.Seller;

public interface SellerRepositoryI {
    void addSeller(Seller user);

    void removeSeller(Seller user);

    boolean isSeller(int userId);

    Seller findSellerById(int userId);

}

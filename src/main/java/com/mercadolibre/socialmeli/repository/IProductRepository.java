package com.mercadolibre.socialmeli.repository;

import com.mercadolibre.socialmeli.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Integer> {
}

package com.example.desafiospring.repositories;

import com.example.desafiospring.entities.Product;

import java.io.IOException;

public interface IProductRepository {

    /**
     * Crea el producto enviado
     * @param product Entidad Product
     * @return Entidad Product con el producto creado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    Product createProduct(Product product) throws IOException;

    /**
     * Consulta el producto asociado a la publicacion enviada
     * @param idPost Id de la publicacion
     * @return Entidad Producto con el producto consultado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    Product getProductByIdPost(Long idPost) throws IOException;

}

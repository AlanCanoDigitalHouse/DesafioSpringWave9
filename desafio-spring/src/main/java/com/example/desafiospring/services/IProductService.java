package com.example.desafiospring.services;

import com.example.desafiospring.dtos.PostDto;
import com.example.desafiospring.dtos.ProductDto;

import java.io.IOException;

public interface IProductService {

    /**
     * Consulta el producto asociado para la publicacion enviada
     * @param post Publicacion a la cual se le consultara su producto
     * @return DTO con el producto consultado
     * @throws IOException
     */
    ProductDto getProductByIdPost(PostDto post) throws IOException;

    /**
     * Crea un producto con la informacion enviada
     * @param productDto DTO con la informacion del producto a crear
     * @param idPost Id de la publicacion a la cual pertenece el producto a crear
     * @return DTO con el producto creado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    ProductDto createProduct(ProductDto productDto, Long idPost) throws IOException;

}

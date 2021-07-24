package com.socialmeli.helpers;

import com.socialmeli.dtos.request.ProductRequestDTO;
import com.socialmeli.models.ProductSocial;

public class ProductHelper {

    /**
     * Mapper between product model objects and DTO products response
     * @param model model to be mapped to DTO request
     * @return DTO request product
     */
    public static ProductRequestDTO mapper(ProductSocial model) {

        return new ProductRequestDTO(
                model.getId(), model.getName(),
                model.getType(), model.getBrand(),
                model.getColor(), model.getNotes()
        );
    }

    /**
     * Mapper between product model objects and DTO products response
     * @param dto DTO request to be mapped to model
     * @return model product
     */
    public static ProductSocial mapper(ProductRequestDTO dto) {
        return new ProductSocial(
                dto.getProduct_id(), dto.getProductName(),
                dto.getType(), dto.getBrand(),
                dto.getColor(), dto.getNotes()
        );
    }
}

package com.meli.desafiospring.DTOs.response;

import com.meli.desafiospring.DTOs.request.DetailRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailResponseDTO {

    Long product_id;
    String productName;
    String type;
    String brand;
    String color;
    String notes;

    public DetailResponseDTO(DetailRequestDTO detailRequestDTO, Long detailProductId) {
        this.product_id = detailProductId;
        this.productName = detailRequestDTO.getProductName();
        this.type = detailRequestDTO.getType();
        this.brand = detailRequestDTO.getBrand();
        this.color = detailRequestDTO.getColor();
        this.notes = detailRequestDTO.getNotes();
    }

    public DetailResponseDTO(Long product_id, String productName, String type, String brand, String color, String notes) {
        this.product_id = product_id;
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }
}

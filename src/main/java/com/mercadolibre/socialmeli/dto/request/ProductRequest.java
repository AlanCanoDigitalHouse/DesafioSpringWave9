package com.mercadolibre.socialmeli.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@Builder
@AllArgsConstructor
public class ProductRequest {
    @NonNull
    private String productName;
    @NonNull
    private String type;
    @NonNull
    private String brand;
    @NonNull
    private String color;
    @NonNull
    private String notes;
}

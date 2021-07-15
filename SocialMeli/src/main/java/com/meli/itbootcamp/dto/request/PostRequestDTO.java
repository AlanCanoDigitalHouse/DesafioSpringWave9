package com.meli.itbootcamp.dto.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PostRequestDTO {

    @NotNull(message = "userId can't be null")
    @Min(message = "userId must be greater than 0", value = 1L)
    private Integer userId;

    private ProductRequestDTO detail;

    @NotNull(message = "category can't be null")
    @Min(message = "category must be greater than 0", value = 1L)
    private Integer category;

    @NotNull(message = "price can't be null")
    @DecimalMin(message = "price must be greater than 1.00", value = "1.0")
    private Double price;

}

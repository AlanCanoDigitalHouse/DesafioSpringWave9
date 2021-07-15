package com.meli.desafiospring.DTOs.request;

import com.meli.desafiospring.DTOs.response.PostResponseDTO;
import com.meli.desafiospring.DTOs.response.DetailResponseDTO;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Validated
public class PostRequestDTO {

    @Range(min=0)
    @Digits(message = "User ID must be made up of numbers.", integer = 15, fraction = 0)
    @NotNull
    Integer userId;
    @Size(min = 8, max = 10, message = "Date format should be dd-mm-yyyy")
    @NotNull
    @NotBlank
    String date;
    @Valid
    DetailRequestDTO detail;
    @NotEmpty(message = "Category is empty.")
    @Size(max = 50, message = "Max category characters length.")
    String category;
    @Max(value = 100000, message = "Price cannot be higher than 100000.")
    Double price;

    public PostResponseDTO toPostResponseDTO(Long id_post, Long detailProductId) {
        DetailResponseDTO detailResponseDTO = new DetailResponseDTO(this.detail, detailProductId);
        return new PostResponseDTO( id_post, this.date, detailResponseDTO, this.category, this.price);
    }

    public String getHello() {
        return "Hello";
    }
}

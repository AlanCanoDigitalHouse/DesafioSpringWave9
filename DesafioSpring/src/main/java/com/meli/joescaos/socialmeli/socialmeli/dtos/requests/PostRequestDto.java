package com.meli.joescaos.socialmeli.socialmeli.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meli.joescaos.socialmeli.socialmeli.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @method Post
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PostRequestDto {

    // Attributes with validations
    @NotNull(message = "UserId must not be null")
    private int userId;

    @NotNull(message = "Date must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    @NotNull(message = "Detail must not be null")
    private Product detail;

    @NotNull(message = "Category must not be null")
    private int category;

    @NotNull(message = "Price must not be null")
    @DecimalMin(message = "Price must be greater than 0.1", value = "0.1")
    private double price;
}

package com.desafiospring.socialmeli.dtos.requests;

import com.desafiospring.socialmeli.dtos.models.ProductDetail;
import com.desafiospring.socialmeli.exceptions.DiscountNullException;
import com.desafiospring.socialmeli.exceptions.UserException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Validated
public class PostRequestDTO {

    @NotNull(message = "User Id no debe ser nulo")
    private Integer userId;

    @NotNull(message = "Date no debe ser nulo")
    private String date;

    @Valid
    private ProductDetail detail;

    @NotNull(message = "Category no debe ser nulo")
    private Integer category;

    @NotNull(message = "Price no debe ser nulo")
    private Double price;

    private boolean hasPromo;
    private Double discount;

    public Double getDiscount() throws UserException {
        if (discount == null) {
            throw new DiscountNullException();
        }
        return discount;
    }
}

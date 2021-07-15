package com.example.desafiospring.dtos;

import com.example.desafiospring.enums.ErrorMessageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Validated
public class ProductCreateDto {

    @NotNull(message = ErrorMessageEnum.PRODUCT_NAME_NOT_NULL)
    @NotBlank(message = ErrorMessageEnum.PRODUCT_NAME_NOT_BLANK)
    private String productName;

    @NotNull(message = ErrorMessageEnum.PRODUCT_TYPE_NOT_NULL)
    @NotBlank(message = ErrorMessageEnum.PRODUCT_TYPE_NOT_BLANK)
    private String type;

    @NotNull(message = ErrorMessageEnum.PRODUCT_BRAND_NOT_NULL)
    @NotBlank(message = ErrorMessageEnum.PRODUCT_BRAND_NOT_BLANK)
    private String brand;

    @NotNull(message = ErrorMessageEnum.PRODUCT_COLOR_NOT_NULL)
    @NotBlank(message = ErrorMessageEnum.PRODUCT_COLOR_NOT_BLANK)
    private String color;

    @NotNull(message = ErrorMessageEnum.PRODUCT_NOTES_NOT_NULL)
    @NotBlank(message = ErrorMessageEnum.PRODUCT_NOTES_NOT_BLANK)
    private String notes;

}

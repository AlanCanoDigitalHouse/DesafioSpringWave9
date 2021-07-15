package com.example.desafiospring.dtos;

import com.example.desafiospring.enums.ConstantEnum;
import com.example.desafiospring.enums.ErrorMessageEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@Validated
public class PostCreateDto {

    @Min(message = ErrorMessageEnum.POST_USERID_MIN, value = 0)
    @NotNull(message = ErrorMessageEnum.POST_USERID_NOT_NULL)
    private Long userId;

    @NotNull(message = ErrorMessageEnum.POST_DATE_NOT_NULL)
    @NotBlank(message = ErrorMessageEnum.POST_DATE_NOT_BLANK)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantEnum.DATE_FORMAT)
    private String date;

    @Valid
    @NotNull(message = ErrorMessageEnum.POST_DETAIL_NOT_NULL)
    private ProductCreateDto detail;

    @Min(message = ErrorMessageEnum.POST_CATEGORY_MIN, value = 0)
    @Max(message = ErrorMessageEnum.POST_CATEGORY_MAX, value = 100)
    @NotNull(message = ErrorMessageEnum.POST_CATEGORY_NOT_NULL)
    private Long category;

    @DecimalMin(message = ErrorMessageEnum.POST_PRICE_MIN, value = "0.0")
    @NotNull(message = ErrorMessageEnum.POST_PRICE_NOT_NULL)
    private Double price;

}

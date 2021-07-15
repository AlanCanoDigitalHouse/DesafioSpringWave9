package com.mercadolibre.socialmeli.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PostDTO {
    @NotNull(message = "User id is mandatory")
    private Integer userId;
    private Integer id_post;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @PastOrPresent(message = "Invalid Date")
    @NotNull(message = "Date is mandatory")
    private LocalDate date;
    @NotNull(message = "A product must be specified")
    @Valid
    private ProductDTO detail;
    @NotNull(message = "Category is mandatory")
    private Integer category;
    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be a positive value")
    private Double price;

}

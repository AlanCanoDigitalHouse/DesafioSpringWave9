package com.kjcelis.social_meli.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {
    private Integer userId;
  //  @NotNull(message = "Please provide a date.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private ProductDTO detail;
    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;

}

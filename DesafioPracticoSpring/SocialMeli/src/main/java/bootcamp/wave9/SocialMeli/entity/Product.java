package bootcamp.wave9.SocialMeli.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Product {

    @JsonProperty("product_id")
    private int productId;

    @NotEmpty(message = "Product name is empty.")
    private String productName;

    @NotEmpty(message = "Product type is empty.")
    private String type;

    @NotEmpty(message = "Product brand is empty.")
    private String brand;

    @NotEmpty(message = "Product color is empty.")
    private String color;

    @NotEmpty(message = "Product notes are empty.")
    private String notes;

}

package challenge1springboot.socialmeli.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Validated
public class ProductDetailDTO {

    @NotBlank(message = "Blank name is not possible")
    @NotNull(message = "Null name is not possible")
    String productName;

    @NotBlank(message = "Blank type is not possible")
    @NotNull(message = "Null type is not possible")
    String type;

    @NotBlank(message = "Blank brand is not possible")
    @NotNull(message = "Null brand is not possible")
    String brand;

    @NotBlank(message = "Blank name is not possible")
    @NotNull(message = "Null name is not possible")
    String color;

    String notes;
}

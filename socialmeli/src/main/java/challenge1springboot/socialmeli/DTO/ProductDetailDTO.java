package challenge1springboot.socialmeli.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ProductDetailDTO {

    @NotBlank(message = "Blank name is not possible")
    @NotNull(message = "Null name is not possible")
    private String productName;

    @NotBlank(message = "Blank type is not possible")
    @NotNull(message = "Null type is not possible")
    private String type;

    @NotBlank(message = "Blank brand is not possible")
    @NotNull(message = "Null brand is not possible")
    private String brand;

    @NotBlank(message = "Blank name is not possible")
    @NotNull(message = "Null name is not possible")
    private String color;

    private String notes;
}

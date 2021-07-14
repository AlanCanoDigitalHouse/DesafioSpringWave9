package challenge1springboot.socialmeli.DTO.request;

import challenge1springboot.socialmeli.DTO.ProductDetailDTO;
import challenge1springboot.socialmeli.globalconstants.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@Validated
public class NewPostRequestDTO {

    @Min(value = 0, message = Message.MIN_VALUE + 0)
    int userId;

    String date;

    @Valid
    ProductDetailDTO detail;

    @Min(value = 0, message = Message.MIN_VALUE + 0)
    int category;

    @DecimalMin(value = "0.1", message = Message.MIN_VALUE + "0.1")
    double price;
}
